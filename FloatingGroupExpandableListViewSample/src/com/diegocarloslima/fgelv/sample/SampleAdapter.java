package com.diegocarloslima.fgelv.sample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SampleAdapter extends BaseExpandableListAdapter {

	private final Context mContext;
	private final LayoutInflater mLayoutInflater;

	private final String[] mGroups = {
			"Cupcake",
			"Donut",
			"Eclair",
			"Froyo",
			"Gingerbread",
			"Honeycomb",
			"Ice Cream Sandwich",
			"Jelly Bean",
			"KitKat"
	};

	private final int[] mGroupDrawables = {
			R.drawable.cupcake,
			R.drawable.donut,
			R.drawable.eclair,
			R.drawable.froyo,
			R.drawable.gingerbread,
			R.drawable.honeycomb,
			R.drawable.ice_cream_sandwich,
			R.drawable.jelly_bean,
			R.drawable.kitkat
	};

	private final String[][] mChilds = {
			{"1.5"},
			{"1.6"},
			{"2.0","2.0.1","2.1"},
			{"2.2","2.2.1","2.2.2","2.2.3"},
			{"2.3","2.3.1","2.3.2","2.3.3","2.3.4","2.3.5","2.3.6","2.3.7"},
			{"3.0","3.1","3.2","3.2.1","3.2.2","3.2.3","3.2.4","3.2.5","3.2.6"},
			{"4.0", "4.0.1", "4.0.2", "4.0.3", "4.0.4"},
			{"4.1", "4.1.1", "4.1.2", "4.2", "4.2.1", "4.2.2", "4.3", "4.3.1"},
			{"4.4"}
	};

	public SampleAdapter(Context context) {
		mContext = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getGroupCount() {
		return mGroups.length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mGroups[groupPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		Log.i("INFO","getGroupView");
		if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_group_item, parent, false);
		}

		final ImageView image = (ImageView) convertView.findViewById(R.id.sample_activity_list_group_item_image);
		image.setImageResource(mGroupDrawables[groupPosition]);

		final TextView text = (TextView) convertView.findViewById(R.id.sample_activity_list_group_item_text);
		text.setText(mGroups[groupPosition]);
		
		final ImageView expandedImage = (ImageView) convertView.findViewById(R.id.sample_activity_list_group_expanded_image);
//		final int resId = isExpanded ? R.drawable.minus : R.drawable.plus;
//		expandedImage.setImageResource(resId);

		expandedImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext,"点击头部条目图片"+groupPosition,Toast.LENGTH_LONG).show();
			}
		});

		final RelativeLayout relativeLayoutHead = (RelativeLayout) convertView.findViewById(R.id.sample_activity_list_group_item_background);
		relativeLayoutHead.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext,"点击头部条目"+groupPosition,Toast.LENGTH_LONG).show();;
			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mChilds[groupPosition].length;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mChilds[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		Log.i("INFO","getChildView");
		if(convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_child_item, parent, false);
		}
		
		final TextView text = (TextView) convertView.findViewById(R.id.sample_activity_list_child_item_text);
		text.setText(mChilds[groupPosition][childPosition]);

//		final LinearLayout llChildren = (LinearLayout) convertView.findViewById(R.id.ll_children);
//		llChildren.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(mContext,"点击子条目"+childPosition,Toast.LENGTH_LONG).show();
//			}
//		});

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
