package com.lzh.weatherforecast.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.util.TitleUtil;
import com.lzh.weatherforecast.widget.TitleLayout;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.socialization.Comment;
import cn.sharesdk.socialization.CommentFilter;
import cn.sharesdk.socialization.CommentListPage;
import cn.sharesdk.socialization.CommentListener;
import cn.sharesdk.socialization.LikeListener;
import cn.sharesdk.socialization.QuickCommentBar;
import cn.sharesdk.socialization.Socialization;
import cn.sharesdk.socialization.component.ReplyTooFrequentlyException;

import static com.mob.tools.utils.R.getStringRes;

/**
 * Created by lzh on 2016/5/13.
 */
public class CommentActivity extends Activity {

    // 模拟的主题id
    private String topicId;
    // 模拟的主题标题
    private String topicTitle;
    // 模拟的主题发布时间
    private String topicPublishTime;
    // 模拟的主题作者
    private String topicAuthor;
    private OnekeyShare oks;
    private QuickCommentBar qcBar;
    private CommentFilter filter;
    private Context context;
    private static final int INIT_SDK = 1;
    private static final int AFTER_LIKE = 2;
    private TitleLayout titleLayout;
    private TitleUtil titleUtil;
    private MyPlatform myPlatform;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_SDK:
                    topicId = getString(R.string.comment_like_id);
                    topicTitle = getString(R.string.comment_like_title);
                    topicPublishTime = getString(R.string.comment_like_publich_time);
                    topicAuthor = getString(R.string.comment_like_author);
                    Socialization service = ShareSDK.getService(Socialization.class);
                    service.setCustomPlatform(new MyPlatform(CommentActivity.this));
                    initOnekeyShare();
                    initQuickCommentBar();
                    break;
                case AFTER_LIKE:
                    if (msg.arg1 == 1) {
                        //success
                        int resId = getStringRes(context, "like_success");
                        if (resId > 0) {
                            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //fail
                        int resId = getStringRes(context, "like_fail");
                        if (resId > 0) {
                            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initData();
        initView();
        setListener();
    }

    private void initData() {
        context = CommentActivity.this;
        ShareSDK.initSDK(this);
        ShareSDK.registerService(Socialization.class);
        myPlatform = new MyPlatform(CommentActivity.this);
        myPlatform.doAuthorize();

        new Thread() {
            public void run() {
                mHandler.sendEmptyMessageDelayed(INIT_SDK, 100);
            }
        }.start();
        titleUtil = new TitleUtil(CommentActivity.this);
        titleUtil.initTitle();
    }

    private void initView() {
        qcBar = (QuickCommentBar) findViewById(R.id.qcBar);
        titleLayout = (TitleLayout) findViewById(R.id.title_layout);
        titleLayout.setTitleMessage(getResources().getString(R.string.suggest));
    }

    private void setListener() {
        //设置评论监听
        Socialization.setCommentListener(new CommentListener() {

            @Override
            public void onSuccess(Comment comment) {
                int resId = getStringRes(context, "ssdk_socialization_reply_succeeded");
                if (resId > 0) {
                    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
                    CommentListPage page = new CommentListPage();
                    page.setTopic(topicId, topicTitle, new MyPlatform(CommentActivity.this).getUserName(), null);
                    page.setCommentFilter(filter);
                    page.setOnekeyShare(oks);
                    page.show(CommentActivity.this, null);
                }
            }

            @Override
            public void onFail(Comment comment) {
                Toast.makeText(context, comment.getFileCodeString(context), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable) {
                if (throwable instanceof ReplyTooFrequentlyException) {
                    int resId = getStringRes(context, "ssdk_socialization_replay_too_frequently");
                    if (resId > 0) {
                        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    throwable.printStackTrace();
                }
            }
        });

        Socialization.setLikeListener(new LikeListener() {

            @Override
            public void onSuccess(String topicId, String topicTitle, String commentId) {
                Message msg = new Message();
                msg.what = AFTER_LIKE;
                msg.arg1 = 1;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFail(String topicId, String topicTitle, String commentId, String error) {
                Message msg = new Message();
                msg.what = AFTER_LIKE;
                msg.arg1 = 2;
                mHandler.sendMessage(msg);
            }

        });
        titleLayout.setLeftListener(new TitleLayout.TitleOnClickListener() {
            @Override
            public void OnClick() {
                finish();
            }
        });
    }

    // Socialization服务依赖OnekeyShare组件，此方法初始化一个OnekeyShare对象
    // 此方法的代码从DemoPage中复制而来
    private void initOnekeyShare() {
        oks = new OnekeyShare();
        oks.setAddress("12345678901");
        oks.setTitle(getString(R.string.ssdk_oks_share));
        oks.setTitleUrl("http://mob.com");
        oks.setText(getString(R.string.share_content));
        oks.setUrl("http://www.mob.com");
        oks.setComment(getString(R.string.ssdk_oks_share));
        oks.setSite(getString(R.string.app_name));
        oks.setSiteUrl("http://mob.com");
        oks.setVenueName("ShareSDK");
        oks.setVenueDescription("This is a beautiful place!");
        oks.disableSSOWhenAuthorize();
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            public void onShare(Platform platform, ShareParams paramsToShare) {
                // 改写twitter分享内容中的text字段，否则会超长，
                // 因为twitter会将图片地址当作文本的一部分去计算长度
                if ("Twitter".equals(platform.getName())) {
                    paramsToShare.setText(platform.getContext().getString(R.string.share_content));
                }
            }
        });
    }

    private void initQuickCommentBar() {
        qcBar.setTextToShare(getString(R.string.share_content));
        qcBar.setAuthedAccountChangeable(false);
        qcBar.setTopic(topicId, topicTitle, topicPublishTime, topicAuthor);
        qcBar.setOnekeyShare(oks);
        qcBar.getBackButton().setVisibility(View.GONE);
        CommentFilter.Builder builder = new CommentFilter.Builder();
        // 非空过滤器
        builder.append(new CommentFilter.FilterItem() {
            // 返回true表示是垃圾评论
            public boolean onFilter(String comment) {
                if (TextUtils.isEmpty(comment)) {
                    return true;
                } else if (comment.trim().length() <= 0) {
                    return true;
                } else if (comment.trim().toLowerCase().equals("null")) {
                    return true;
                }
                return false;
            }

            @Override
            public int getFilterCode() {
                return 0;
            }
        });
        // 字数上限过滤器
        builder.append(new CommentFilter.FilterItem() {
            // 返回true表示是垃圾评论
            public boolean onFilter(String comment) {
                if (comment != null) {
                    String pureComment = comment.trim();
                    String wordText = com.mob.tools.utils.R.toWordText(pureComment, 140);
                    if (wordText.length() != pureComment.length()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public int getFilterCode() {
                return 0;
            }
        });
        filter = builder.build();
        qcBar.setCommentFilter(filter);
        qcBar.setOnekeyShare(oks);
    }

}
