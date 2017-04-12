package com.example.vm.comoflyer;

import com.example.vm.demtools.HgtReader;
//import javafx.util.Pair;
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;

public class Panorama {

    public static float[][] getLinearizedDistanceMatrix() {
        OffscreenComoFlyer app = new OffscreenComoFlyer(49.182708, 20.048567);
        float[][] distanceMatrix = app.getImages();
        float[][] linearizedMatrix = StaticDepthHelpers.linearize(distanceMatrix, OffscreenComoFlyer.FRUSTUM_NEAR_PLANE * (HgtReader.METERS_IN_SECOND / HgtReader.TERRAIN_SCALE), OffscreenComoFlyer.FRUSTUM_FAR_PLANE * (HgtReader.METERS_IN_SECOND / HgtReader.TERRAIN_SCALE));
//        BufferedImage depthMaskNormalized = StaticDepthHelpers.getDepthImage(distanceMatrix);
//        File outputPanorama = new File("panorama.png");
//        File outputDepth = new File("depth.png");
//        try {
//            ImageIO.write(images.getKey(), "png", outputPanorama);
//            ImageIO.write(depthMaskNormalized, "png", outputDepth);
//            StaticDepthHelpers.saveToCsv(linearizedMatrix, "linearized.csv");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return linearizedMatrix;
    }

}