import React, {Component} from 'react';
import Input from "../../components/Input";
import Select from "../../components/Select";
import Button from "../../components/Button";

class BrandsTab extends Component {
    constructor(props) {
        super(props);
        this.state = {
            brands: [],
            brandId: null,
            brandName: "",
            brandYear: null,
            newError: "",
            modifyError: ""
        }
    }

    addNewBrand = () => {

    };


    render() {
        const {brands} = this.state;
        brands.unshift({id: null, text: "None"});

        return (
            <div className="tab-content">
                <div className="card cuarter-size">
                    <h3 className="subtitle">Add a new brand</h3>
                    <Input className="admin-input" placeholder="Brand name"/>
                    <br/>
                    <Input className="admin-input" placeholder="Year of birth"/>
                    <br/>
                    <Button text="Send" onClick={this.addNewBrand}/>
                    <p>{this.state.newError}</p>
                </div>
                <div className="card cuarter-size">
                    <h3 className="subtitle">Modify a brand</h3>
                    <Select options={brands}/>

                </div>
            </div>
        );
    }
}

export default BrandsTab;
