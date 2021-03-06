/*
 * Copyright (C) 2017 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package layers.mercatortiledimagelayer.esri;

import gov.nasa.worldwind.avlist.*;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.mercator.*;
import gov.nasa.worldwind.util.*;

import java.net.*;

public class EsriWorldStreetMapLayer extends BasicMercatorTiledImageLayer {
    public static final String LAYERNAME = "New Open Street Map";

    public EsriWorldStreetMapLayer() {
        super(makeLevels());
        this.setName(LAYERNAME);

    }

    private static LevelSet makeLevels() {
        AVList params = new AVListImpl();

        params.setValue(AVKey.TILE_WIDTH, 256);				
        params.setValue(AVKey.TILE_HEIGHT, 256);
        params.setValue(AVKey.DISPLAY_NAME, LAYERNAME);
        params.setValue(AVKey.NAME, LAYERNAME);
        params.setValue(AVKey.DATA_CACHE_NAME, "Esri/WorldStreetMap");
       params.setValue(AVKey.SERVICE, "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
      //  params.setValue(AVKey.SERVICE, "http://tile.openstreetmap.org");
      //  tile.openstreetmap.org/
        params.setValue(AVKey.DATASET_NAME, LAYERNAME);
        params.setValue(AVKey.FORMAT_SUFFIX, ".png");		
        params.setValue(AVKey.NUM_LEVELS, 16);				
        params.setValue(AVKey.NUM_EMPTY_LEVELS, 0);		
        //params.setValue(AVKey.LEVEL_ZERO_TILE_DELTA, new LatLon(Angle.fromDegrees(45d), Angle.fromDegrees(45d)));//åˆ�å§‹ç“¦ç‰‡è¦†ç›–ç»�çº¬åº¦å¤§å°�
        params.setValue(AVKey.LEVEL_ZERO_TILE_DELTA, new LatLon(Angle
            .fromDegrees(22.5d), Angle.fromDegrees(45d)));
        //params.setValue(AVKey.SECTOR, Sector.FULL_SPHERE);	
        params.setValue(AVKey.SECTOR, new MercatorSector(-1.0, 1.0, Angle.NEG180, Angle.POS180));
        params.setValue(AVKey.TILE_URL_BUILDER, new URLBuilder());

        return new LevelSet(params);
    }


    private static class URLBuilder implements TileUrlBuilder {
        public URL getURL(Tile tile, String imageFormat)
            throws MalformedURLException
        {

            int row =  (1 << (tile.getLevelNumber()) + 3) - 1 - tile.getRow();
            int col = tile.getColumn();
            int level = tile.getLevelNumber()+3;
            String serverURL = tile.getLevel().getService();
            //tile URL
            String fullurl = serverURL + "/tile/" + level + "/" + row + "/" + col;


          //  System.out.println("fullurl:"+fullurl);
            return new URL(fullurl);
        }
    }
}
