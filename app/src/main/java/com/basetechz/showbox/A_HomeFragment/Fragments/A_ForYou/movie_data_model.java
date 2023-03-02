package com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou;

public class movie_data_model {
    private String sf_Poster,sf_MovieTxt,sf_Views,sf_Id;

    movie_data_model(){

    }

    public movie_data_model(String sf_poster, String sf_movieTxt, String sf_views, String sf_id) {
        sf_Poster = sf_poster;
        sf_MovieTxt = sf_movieTxt;
        sf_Views = sf_views;
        sf_Id = sf_id;
    }

    public String getSf_Poster() {
        return sf_Poster;
    }

    public void setSf_Poster(String sf_Poster) {
        this.sf_Poster = sf_Poster;
    }

    public String getSf_MovieTxt() {
        return sf_MovieTxt;
    }

    public void setSf_MovieTxt(String sf_MovieTxt) {
        this.sf_MovieTxt = sf_MovieTxt;
    }

    public String getSf_Views() {
        return sf_Views;
    }

    public void setSf_Views(String sf_Views) {
        this.sf_Views = sf_Views;
    }

    public String getSf_Id() {
        return sf_Id;
    }

    public void setSf_Id(String sf_Id) {
        this.sf_Id = sf_Id;
    }
}
