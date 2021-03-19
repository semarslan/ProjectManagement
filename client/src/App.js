import './App.css';
import Dashboard from "./components/Dashboard.jsx";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Route} from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import {Provider} from "react-redux";
import store from "./redux/store";
import UpdateProject from "./components/Project/UpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTasks/UpdateProjectTask";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";
import jwtDecode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import {SET_CURRENT_USER} from "./actions/types";

const jwtToken = localStorage.jwtToken

if (jwtToken) {
    setJWTToken(jwtToken)
    const decoded_jwtToken = jwtDecode(jwtToken);
    store.dispatch({
        type: SET_CURRENT_USER,
        payload: decoded_jwtToken
    });

    const currentTime = Date.now()/1000
    if (decoded_jwtToken.exp < currentTime) {
       // window.location.href= "/";
    }
}


function App() {
    return (
        <Provider store={store}>
            <Router>
                <div className="App">
                    <Header />
                    {
                        //Public routes
                    }
                    <Route exact path={"/"} component={Landing} />
                    <Route exact path={"/register"} component={Register} />
                    <Route exact path={"/login"} component={Login} />
                    {
                        //Private routes
                    }

                    <Route exact path={"/dashboard"} component={Dashboard}/>
                    <Route exact path={"/addProject"} component={AddProject}/>
                    <Route exact path={"/updateProject/:id"} component={UpdateProject}/>
                    <Route exact path={"/projectBoard/:id"} component={ProjectBoard}/>
                    <Route exact path={"/addProjectTask/:id"} component={AddProjectTask}/>
                    <Route exact path={"/updateProjectTask/:backlogId/:projectTaskId"} component={UpdateProjectTask}/>
                </div>
            </Router>
        </Provider>
    );
}


export default App;
