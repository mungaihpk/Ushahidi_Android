/**
 ** Copyright (c) 2010 Ushahidi Inc
 ** All rights reserved
 ** Contact: team@ushahidi.com
 ** Website: http://www.ushahidi.com
 **
 ** GNU Lesser General Public License Usage
 ** This file may be used under the terms of the GNU Lesser
 ** General Public License version 3 as published by the Free Software
 ** Foundation and appearing in the file LICENSE.LGPL included in the
 ** packaging of this file. Please review the following information to
 ** ensure the GNU Lesser General Public License version 3 requirements
 ** will be met: http://www.gnu.org/licenses/lgpl.html.
 **
 **
 ** If you have questions regarding the use of this file, please contact
 ** Ushahidi developers at team@ushahidi.com.
 **
 **/
package com.ushahidi.android.app.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.google.android.maps.MapView;
import com.ushahidi.android.app.R;
import com.ushahidi.android.app.adapters.ListPhotoAdapter;

/**
 * @author eyedol
 * 
 */
public class ViewCheckinView extends com.ushahidi.android.app.views.View {

	public TextView name;
	public TextView message;
	public TextView date;
	public ViewAnimator viewCheckinRoot;
	public LayoutInflater inflater;
	public ListView listPhotos;
	public TextView listPhotosEmptyView;
	public ListPhotoAdapter photoAdapter;
	public MapView mapView;
	private Context context;

	public ViewCheckinView(Activity activity) {
		super(activity);
		this.context = activity;
		this.viewCheckinRoot = (ViewAnimator) activity
				.findViewById(R.id.view_checkin_root);

		this.mapView = (MapView) activity.findViewById(R.id.loc_map);

		name = (TextView) activity.findViewById(R.id.checkin_title);

		this.message = (TextView) activity
				.findViewById(R.id.checkin_description);

		this.date = (TextView) activity.findViewById(R.id.checkin_date);

		this.photoAdapter = new ListPhotoAdapter(activity);

		this.listPhotos = (ListView) activity.findViewById(R.id.list_photos);

		this.listPhotosEmptyView = (TextView) activity
				.findViewById(R.id.empty_photo_list);

		if (this.listPhotosEmptyView != null) {
			this.listPhotos.setEmptyView(listPhotosEmptyView);
		}

		this.inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public View filterCheckin() {
		View v = this.inflater.inflate(R.layout.list_checkin_header, null);
		return v;
	}
	
	public void setTitle(String title) {
		this.name.setText(title);
	}

	public String getTitle() {
		return this.getTitle().toString();
	}


	public void setDate(String date) {
		this.date.setText(date);
	}

	public String getDate() {
		return this.date.getText().toString();
	}

	public void setBody(String body) {
		this.message.setText(body);
	}

	public String getBody() {
		return this.message.getText().toString();
	}

	public void setListPhotos(int reportId) {
		if (listPhotos != null) {
			ListPhotoAdapter adapter = new ListPhotoAdapter(context);
			adapter.refresh(reportId);
			listPhotos.setAdapter(adapter);
		}
	}

	public ListView getListPhotos() {
		return this.listPhotos;
	}

	public void goNext() {

		Animation in = AnimationUtils.loadAnimation(context,
				R.anim.slide_right_in);
		viewCheckinRoot.startAnimation(in);
	}

	public void goPrevious() {
		Animation out = AnimationUtils.loadAnimation(context,
				R.anim.slide_left_in);
		viewCheckinRoot.startAnimation(out);
	}

}
