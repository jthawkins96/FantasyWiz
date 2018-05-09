package eduinfo_capstone_2018team_07.iu.httpsgithub.capstonetemplate;

/**
 * Created by bgorman on 1/10/18.
 */

public class Player {
    String name, position, team, picture, projections;
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public String getTeam(){
        return team;
    }
    public void setTeam(String team){
        this.team = team;
    }
    public  String getPicture(){
        return picture;
    }
    public void setPicture(String picture){
        this.picture = picture;
    }
    public String getProjection(){
        return projections;
    }
    public void setProjections(String projections){
        this.projections = projections;
    }

}
