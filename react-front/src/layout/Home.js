import React, {Component} from 'react';
import {withRouter} from "react-router-dom";
import Select from "../components/Select";
import Slider from "../components/Slider";
import Video from "../components/Video";
import sourceVideo from "../media/videos/home_video.mp4"
import Button from "../components/Button";

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            brandOptions: [
                {
                    id: null,
                    text: "All Brands"
                },
                {
                    id: null,
                    text: "LOL"
                }
            ],
            vehicleOptions: [
                {
                    id: null,
                    text: "All Models"
                },
                {
                    id: null,
                    text: "Serie 3"
                },
                {
                    id: null,
                    text: "Cayman"
                }
            ]
        }
    }


    render() {
        const {brandOptions, vehicleOptions} = this.state;

        return (
            <div>
                <div className="video-box">
                    <div className="overlay">
                        <div className="text-overlay">
                            <h1 style={{color: "white", fontSize: "3.25rem"}} className="title"
                            >Find The Vehicle Of Your Dreams</h1>
                            <div className="row">
                                <div className="columna row-item">
                                    <Select options={brandOptions}/>
                                    <p>Minimum price</p>
                                    <Slider/>
                                </div>
                                <div className="columna row-item">
                                    <Select options={vehicleOptions}/>
                                    <p>Maximum price</p>
                                    <Slider/>
                                </div>
                            </div>
                            <div className="row">
                                <Button type="primary is-success" text="Search"/>
                            </div>
                        </div>


                        <Video className="home-video" width={1280} height={720} source={sourceVideo}/>
                    </div>
                    <div className="container is-widescreen is-centered">
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(Home);
