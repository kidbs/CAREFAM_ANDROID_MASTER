package com.jgy.animal.fragment.mainUsed;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.jgy.animal.R;
import com.kakao.sdk.common.KakaoSdk;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

public class MapFrag extends Fragment {
    private RelativeLayout mMapViewContainer;
    private MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mapView = inflater.inflate(R.layout.frag_map, container, false);

        KakaoSdk.init(requireContext(), "8f549455645326252d3b74f377477d3d");
        mMapViewContainer = mapView.findViewById(R.id.map_view);

        mMapView = new MapView(requireContext());
        //mMapView.setDaumMapApiKey(MapApiConst.DAUM_MAPS_ANDROID_APP_API_KEY);
        mMapView.setMapType(MapView.MapType.Standard);

        //////////////////////////////////////////////////////////////////////////////////////////////
        // 마커
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(35.866085, 128.593803); // 코리아-it-아카데미 의 위도와 경도(특정위치의 위도경도)
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("코리아 IT 아카데미 대구지점");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mMapView.addPOIItem(marker);

        mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.866085, 128.593803), true); //지도의 중심점 설정
        // 줌 레벨 변경
        mMapView.setZoomLevel(7, true);
        // 중심점 변경 + 줌 레벨 변경
        // mMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);
        // 줌 인
        mMapView.zoomIn(true);
        // 줌 아웃
        mMapView.zoomOut(true);
        mMapViewContainer.addView(mMapView);

        // 현재 위치를 마커로 표시
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////

        return mapView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            }
        }
    }
}
