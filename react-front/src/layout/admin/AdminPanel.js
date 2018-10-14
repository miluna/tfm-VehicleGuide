import React, {Component} from 'react';
import Brands from './BrandsTab';
import Vehicles from './EnginesTab';
import Engines from './EnginesTab';


class AdminPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tabs: [
                {
                    title: "Brands",
                    content: <Brands/>
                },
                {
                    title: "Vehicles",
                    content: <Vehicles/>
                },
                {
                    title: "Engines",
                    content: <Engines/>
                }
            ],
            activeTabIndex: 0
        }
    }

    changeTab = (e) => {
        const text = e.target.innerHTML;
        const {tabs} = this.state;
        const selected = tabs.findIndex(e => e.title === text);
        this.setState({activeTabIndex: selected});
    };

    render() {
        const {tabs, activeTabIndex} = this.state;

        return (
            <div className="container">
                <h2 className="title">Admin Panel</h2>

                <div className="card tab-container">
                    {tabs.map(e => {
                        if (tabs[activeTabIndex].title === e.title) {
                            return <button key={e.title} className="tab-panel tab-active">{e.title}</button>
                        } else {
                            return <button key={e.title} className="tab-panel tab-inactive" onClick={this.changeTab}>{e.title}</button>
                        }
                    })}
                    {tabs[activeTabIndex].content}
                </div>
            </div>
        );
    }
}

export default AdminPanel;
