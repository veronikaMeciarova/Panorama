package com.example.vm.comoflyer;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.example.vm.demtools.HgtReader;
import android.util.Pair;

public abstract class GeoApplication extends SimpleApplication {

    private static final String APP_LOCATION = "C:\\Users\\VM\\Documents\\MatFyz\\Bakalarka\\ComoFlyer\\";

    int latitude;
    int latitudeSeconds;
    int longitude;
    int longitudeSeconds;

    int heightCorrectionInMeters = 0;

    public GeoApplication(double lat, double lon){
        this(   (int) lat,
                (int)((lat - (int) lat)* HgtReader.SRTM_1_SIZE),
                (int) lon,
                (int)((lon - (int) lon)*HgtReader.SRTM_1_SIZE));
    }

    public GeoApplication(double lat, double lon, int heightCorrectionInMeters){
        this(lat,lon);
        this.heightCorrectionInMeters = heightCorrectionInMeters;
    }

    public GeoApplication(int latitude, int latitudeSeconds, int longitude, int longitudeSeconds) {
        this.latitude = latitude;
        this.latitudeSeconds = latitudeSeconds;
        this.longitude = longitude;
        this.longitudeSeconds = longitudeSeconds;
    }

    public GeoApplication(int latitude, int latitudeSeconds, int longitude, int longitudeSeconds, int heightCorrectionInMeters) {
        this(latitude, latitudeSeconds, longitude, longitudeSeconds);
        this.heightCorrectionInMeters = heightCorrectionInMeters;
    }

    Node setSomeCommonSettings(){
        assetManager.registerLocator(APP_LOCATION, FileLocator.class);

        Node mainScene = new Node("Panorama Scene");
        rootNode.attachChild(mainScene);

        Pair<TerrainQuad,Float> pair = StaticHelpers.createTerrain(assetManager, latitude, latitudeSeconds, longitude, longitudeSeconds);
        float positionHeight = pair.second;
        mainScene.attachChild(pair.first);
        mainScene.addLight(StaticHelpers.getSun());
        mainScene.addLight(StaticHelpers.getLight());
        mainScene.attachChild(StaticHelpers.getSky(assetManager));

        cam.setFrustumFar(30000);
        cam.setLocation(new Vector3f(0, (positionHeight + heightCorrectionInMeters) / HgtReader.TERRAIN_SCALE, 0));

        return mainScene;
    }

}
