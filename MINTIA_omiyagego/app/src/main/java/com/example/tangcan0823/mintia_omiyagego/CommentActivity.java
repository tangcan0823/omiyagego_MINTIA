package com.example.tangcan0823.mintia_omiyagego;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kii.cloud.storage.Kii;
import com.kii.cloud.storage.KiiBucket;
import com.kii.cloud.storage.KiiObject;
import com.kii.cloud.storage.KiiUser;
import com.kii.cloud.storage.callback.KiiObjectCallBack;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by shikou on 2016/08/27.
 */
public class CommentActivity extends AppCompatActivity {
    private String goods_id;
    private String[] mStringTimeList;
    private String[] mStringCommentList;
    private int Number = 0;
    private ListView mListViewEventList;
    private ArrayAdapter<String> mEventListAdapter = null;
    private Button mButtonSendComment;
    private EditText mEditCommentContext;
    private String commentContext;
    private ImageButton mImageButtonRefresh;
    private ImageButton mImageButtonBack;
    private ImageButton mImageButtonStar1;
    private ImageButton mImageButtonStar2;
    private ImageButton mImageButtonStar3;
    private ImageButton mImageButtonStar4;
    private ImageButton mImageButtonStar5;
    private String time = "";
    private int starNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        mListViewEventList = (ListView)findViewById(R.id.listView_comment_list);
        mEventListAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_text_list_item);

        Bundle bundle = this.getIntent().getExtras();
        goods_id = bundle.getString("GOODS_ID");

        final KiiObject comment_list_object = KiiObject.createByUri(Uri.parse(
                "kiicloud://buckets/goods_comment_buckets/objects/" + goods_id));

        comment_list_object.refresh(new KiiObjectCallBack() {
            @Override
            public void onRefreshCompleted(int token, KiiObject object, Exception exception) {
                if (exception != null) {
                    // Error handling
                    return;
                }
                //HashSetの値を取り出すためにIteratorを使う、取り出す順番はバラバラになる点に注意
                HashSet<String> keyset = object.keySet();
                mStringTimeList = keyset.toArray(new String[keyset.size()]);
                mStringCommentList = keyset.toArray(new String[keyset.size()]);

                Iterator iterator = keyset.iterator();
                while(iterator.hasNext()){
                    mStringCommentList[Number++] =comment_list_object.getString(iterator.next().toString());
                }

                for (int i = 0; i < Number; i ++) {
                    mStringCommentList[i] = "時間: " + mStringTimeList[i] + "\n" + "内容: " + mStringCommentList[i];
                }

                Arrays.sort(mStringCommentList);

                for (int i = Number-1; i >= 0; i --) {
                    mEventListAdapter.add(mStringCommentList[i]);
                }
                mListViewEventList.setAdapter(mEventListAdapter);

                Number = 0;
            }
        });

        mImageButtonRefresh = (ImageButton) findViewById(R.id.imageButton_comment_updata);
        mImageButtonRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                mEventListAdapter.clear();
                comment_list_object.refresh(new KiiObjectCallBack() {
                    @Override
                    public void onRefreshCompleted(int token, KiiObject object, Exception exception) {
                        if (exception != null) {
                            // Error handling
                            return;
                        }
                        //HashSetの値を取り出すためにIteratorを使う、取り出す順番はバラバラになる点に注意
                        HashSet<String> keyset = object.keySet();
                        mStringTimeList = keyset.toArray(new String[keyset.size()]);
                        mStringCommentList = keyset.toArray(new String[keyset.size()]);

                        Iterator iterator = keyset.iterator();
                        while(iterator.hasNext()){
                            mStringCommentList[Number++] =comment_list_object.getString(iterator.next().toString());
                        }

                        for (int i = 0; i < Number; i ++) {
                            mStringCommentList[i] = "時間: " + mStringTimeList[i] + "\n" + "内容: " + mStringCommentList[i];
                        }

                        Arrays.sort(mStringCommentList);

                        for (int i = Number-1; i >= 0; i --) {
                            mEventListAdapter.add(mStringCommentList[i]);
                        }
                        mListViewEventList.setAdapter(mEventListAdapter);

                        Number = 0;
                    }
                });
            }
        });

        mImageButtonBack = (ImageButton) findViewById(R.id.imageButton_back_to_former);
        mImageButtonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });

        mImageButtonStar1 = (ImageButton) findViewById(R.id.imageButton_star1);
        mImageButtonStar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                starNumber = 1;
                mImageButtonStar1.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar2.setImageResource(R.drawable.ic_star_pre);
                mImageButtonStar3.setImageResource(R.drawable.ic_star_pre);
                mImageButtonStar4.setImageResource(R.drawable.ic_star_pre);
                mImageButtonStar5.setImageResource(R.drawable.ic_star_pre);
            }
        });

        mImageButtonStar2 = (ImageButton) findViewById(R.id.imageButton_star2);
        mImageButtonStar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                starNumber = 2;
                mImageButtonStar1.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar2.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar3.setImageResource(R.drawable.ic_star_pre);
                mImageButtonStar4.setImageResource(R.drawable.ic_star_pre);
                mImageButtonStar5.setImageResource(R.drawable.ic_star_pre);
            }
        });

        mImageButtonStar3 = (ImageButton) findViewById(R.id.imageButton_star3);
        mImageButtonStar3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                starNumber = 3;
                mImageButtonStar1.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar2.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar3.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar4.setImageResource(R.drawable.ic_star_pre);
                mImageButtonStar5.setImageResource(R.drawable.ic_star_pre);
            }
        });

        mImageButtonStar4 = (ImageButton) findViewById(R.id.imageButton_star4);
        mImageButtonStar4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                starNumber = 4;
                mImageButtonStar1.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar2.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar3.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar4.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar5.setImageResource(R.drawable.ic_star_pre);
            }
        });

        mImageButtonStar5 = (ImageButton) findViewById(R.id.imageButton_star5);
        mImageButtonStar5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                starNumber = 5;
                mImageButtonStar1.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar2.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar3.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar4.setImageResource(R.drawable.ic_star_beh);
                mImageButtonStar5.setImageResource(R.drawable.ic_star_beh);
            }
        });

        mEditCommentContext = (EditText)findViewById(R.id.editText_comment);
        mButtonSendComment = (Button)findViewById(R.id.button_send_comment);
        mButtonSendComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (starNumber == 0){
                    Toast.makeText(CommentActivity.this, "星を付けてください！", Toast.LENGTH_LONG).show();
                }
                else if(mEditCommentContext.length() == 0){
                    Toast.makeText(CommentActivity.this, "何とか一言書いてください", Toast.LENGTH_LONG).show();
                }
                else{
                    commentContext = mEditCommentContext.getText().toString();
                    Calendar calendar = Calendar.getInstance();
                    time = time + calendar.get(Calendar.YEAR) + "年";

                    if(calendar.get(Calendar.MONTH) <9 )
                        time = time + "0";
                    time = time + (calendar.get(Calendar.MONTH) + 1) + "月";

                    if(calendar.get(Calendar.DATE) <10 )
                        time = time + "0";
                    time = time + calendar.get(Calendar.DATE) + "日";

                    if(calendar.get(Calendar.HOUR_OF_DAY) <10 )
                        time = time + "0";
                    time = time + calendar.get(Calendar.HOUR_OF_DAY) + ":";

                    if(calendar.get(Calendar.MINUTE) <10 )
                        time = time + "0";
                    time = time + calendar.get(Calendar.MINUTE) + ":";

                    if(calendar.get(Calendar.SECOND) <10 )
                        time = time + "0";
                    time = time + calendar.get(Calendar.SECOND);

                    comment_list_object.refresh(new KiiObjectCallBack() {
                        @Override
                        public void onRefreshCompleted(int token, KiiObject object, Exception exception) {
                            if (exception != null) {
                                // Error handling
                                return;
                            }
                        }
                    });

                    time = time + " (" + starNumber + "星)";

                    //コメントをObjectに登録
                    comment_list_object.set(time , commentContext);

                    time = "";
                    comment_list_object.save(new KiiObjectCallBack() {
                        @Override
                        public void onSaveCompleted(int token, KiiObject object, Exception exception) {
                            if (exception != null) {
                                // Error handling
                                Toast.makeText(CommentActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                    Toast.makeText(CommentActivity.this, "コメント済み！ありがとうございます！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
