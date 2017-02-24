package ec.edu.epn.guiaquito.services.impl;

import ec.edu.epn.guiaquito.entities.PointOfInterest;
import ec.edu.epn.guiaquito.services.PointOfInterestService;
import ec.edu.epn.guiaquito.services.base.BaseServiceImpl;
import net.sf.sprockets.google.Place;
import net.sf.sprockets.google.Places;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Stateless
public class PointOfInterestServiceImpl extends BaseServiceImpl<PointOfInterest, Integer> implements PointOfInterestService {

    @Override
    public List<PointOfInterest> queryFromGoogle(double latitude, double longitude, String[] campos) {
        List<Place> results = null;
        try {
            results = Places.nearbySearch(
                    Places.Params.create().latitude(latitude)
                            .longitude(longitude).radius(500).addTypes(campos))
                    .getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addDescriptionFromWikipedia(results, campos);
    }

    @Override
    public List<PointOfInterest> addDescriptionFromWikipedia(List<Place> places, String[] interests) {
        List<Place> sortedPoints = filterPlaces(places, interests);
        List<PointOfInterest> pois = new ArrayList<>();

        for (Place place : sortedPoints) {
            String description = getDescriptionFromWikipedia(place.getLatitude(), place.getLongitude());
            PointOfInterest poi = new PointOfInterest(place.getLatitude(),
                    place.getLongitude(), place.getName(), place.getRating(), description, place.getTypes());
            pois.add(poi);
        }
        return pois;
    }

    private List<Place> filterPlaces(List<Place> places, String[] interests) {
        List<Place> filteredPlaces = null;
//        if (interests.length == 0) {
        filteredPlaces = sortPlaces(places).subList(0, 3);
//        }
        return filteredPlaces;
    }

    private List<Place> sortPlaces(List<Place> places) {
        List<Place> pois = new ArrayList<>(places);
        if (pois.size() > 0) {
            Comparator<Place> comparator = new Comparator<Place>() {
                @Override
                public int compare(Place place1, Place place2) {
                    return new Float(place2.getRating()).compareTo(place1.getRating());
                }
            };
            Collections.sort(pois, comparator);
        }
        return pois;
    }

    private String getDescriptionFromWikipedia(Double latitude, Double longitude) {
        String description = "";
        String geoSearchUrl = "https://es.wikipedia.org/w/api.php?action=query&format=json&formatversion=2&rawcontinue=true&list=geosearch&gsradius=20&gscoord="
                + latitude + "|" + longitude;
        String pageId = "";
        try {
            URL url = new URL(geoSearchUrl);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            JSONObject results = new JSONObject(sb.toString());
            JSONObject query = new JSONObject(results.getString("query"));
            JSONArray geoSearch = new JSONArray(query.getString("geosearch"));
            JSONObject geoSearchJSONObject = geoSearch.getJSONObject(0);
            pageId = geoSearchJSONObject.getString("pageid");
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String extract = "";
        if (pageId.compareTo("") != 0) {
            String pageSearchUrl = "https://es.wikipedia.org/w/api.php?action=query&format=json&formatversion=2&rawcontinue=true&pageids="
                    + pageId + "&prop=extracts|info&exsentences=2";
            try {
                URL url = new URL(pageSearchUrl);
                URLConnection con = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject results = new JSONObject(sb.toString());
                JSONObject query = new JSONObject(results.getString("query"));
                JSONArray pages = new JSONArray(query.getString("pages"));
                JSONObject pagesObj = pages.getJSONObject(0);
                extract = pagesObj.getString("extract");
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        description = extract;

        return description;

    }
}
