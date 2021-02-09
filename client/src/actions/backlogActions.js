import axios from "axios";
import {GET_ERRORS, GET_BACKLOG, GET_PROJECT_TASK, DELETE_PROJECT_TASK} from "./types";

export const addProjectTask = (backlogId, projectTask, history) =>
    async dispatch => {
        try {
            await axios.post(`/api/backlog/${backlogId}`, projectTask);
            history.push(`/projectBoard/${backlogId}`);
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

export const getBacklog = (backlogId) =>
    async dispatch => {
        try {
            const res = await axios.get(`/api/backlog/${backlogId}`);
            dispatch({
                type: GET_BACKLOG,
                payload: res.data
            })
        } catch (e) {
            dispatch({
                type: GET_ERRORS,
                payload: e.response.data
            });
        }
    }

export const getProjectTask = (backlogId, projectTaskId, history) =>
    async dispatch => {
        try {
            const res = await axios.get(`/api/backlog/${backlogId}/${projectTaskId}`);
            dispatch({
                type: GET_PROJECT_TASK,
                payload: res.data
            })
        } catch (e) {
            history.push("/dashboard");
        }
    };

export const updateProjectTask = (backlogId, projectTaskId, projectTask, history) =>
    async dispatch => {
        try {
            await axios.patch(`/api/backlog/${backlogId}/${projectTaskId}`, projectTask);
            history.push(`/projectBoard/${backlogId}`)
            dispatch({
                type: GET_ERRORS,
                payload: {}
            })
        } catch (e) {
            dispatch({
                type: GET_ERRORS,
                payload: e.response.data
            })
        }
    }

export const deleteProjectTask = (backlogId, projectTaskId) =>
    async dispatch => {
        if (window.confirm(`You are deleting project task ${projectTaskId}, this action cannot be undone`)) {
            await axios.delete(`/api/backlog/${backlogId}/${projectTaskId}`)
            dispatch({
                type: DELETE_PROJECT_TASK,
                payload: projectTaskId
            })
        }

    }