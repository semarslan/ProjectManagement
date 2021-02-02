import axios from "axios";
import {GET_ERRORS, GET_PROJECT, GET_PROJECTS} from "./types";


export const createProject = (project, history) => async dispatch => {
    try {
        const res = await axios.post("http://localhost:8080/api/project", project);
        history.push("/dashboard");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });

    } catch (e) {
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        });

    }
}

export const getProjects = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/api/project");
    dispatch({
        type: GET_PROJECTS,
        payload: res.data
    })
}

export const getProject = (id, history) => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8080/api/project/${id}`);
        dispatch({
            type: GET_PROJECT,
            payload: res.data
        });
    } catch (err) {
        history.push("/dashboard");
    }


};


