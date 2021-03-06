import axios from "axios";
import {GET_ERRORS, SET_CURRENT_USER} from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwtDecode from "jwt-decode";

export const createUser = (user, history) => async dispatch => {
    try {
        await axios.post("/api/users/register", user);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (e) {
        dispatch({
                type: GET_ERRORS,
                payload: e.response.data

            }
        )}
}

export const login = (LoginRequest,  history) => async dispatch => {

    try {
        //post => login request
        const res = await axios.post("/api/users/login", LoginRequest)

        //extract token from res.data
        const {token} = res.data;

        //store the  token in the localStorage
        localStorage.setItem("jwtToken", token);

        //set our token in header
        setJWTToken(token);

        // decode token on React
        const decoded = jwtDecode(token);

        // dispatch to our securityReducer
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded
        });
    }catch (e) {
        dispatch({
            type: GET_ERRORS,
            payload: e.response.data
        });
    }
}

export const logout = () => dispatch => {
    localStorage.removeItem("jwtToken");
    setJWTToken(false);
    dispatch({
        type: SET_CURRENT_USER,
        payload: {}
    });
}
// export const loginSuccess = authState => {
//     return {
//         type: ACTIONS.LOGIN_SUCCESS,
//         payload: authState
//     };
// };
//
// export const loginHandler = credentials => {
//     return async function(dispatch) {
//         const response = await login(credentials);
//         const authState = {
//             ...response.data.user,
//             password: credentials.password,
//             token: response.data.token
//         };
//         dispatch(loginSuccess(authState));
//         return response;
//     };
// };