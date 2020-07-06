import { BehaviorSubject } from 'rxjs';
import axios from 'axios';

const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));

export const authService = {
    login,
    register,
    logout,
    currentUser: currentUserSubject.asObservable(),
    get currentUserValue () { return currentUserSubject.value }
};

function login(username, password) {
    return axios.post("http://localhost:8080/user/login", 
        JSON.stringify({ username, password }),
            {
                headers: { 'Content-Type': 'application/json' }
            }
        ).then( response => {

            let jwtData = response.data.split(' ')[1]
            var jwtDecode = require('jwt-decode');

            // let decodedJwtJsonData = window.atob(jwtData)
            let decodedJwtData = jwtDecode(jwtData)
            console.log("Decoded data : " + decodedJwtData.authorities[0].substring(5))
            console.log(decodedJwtData)


            var curUser = {
                token: response.data,
                role: decodedJwtData.authorities[0].substring(5)
            }

            localStorage.setItem('currentUser', JSON.stringify(curUser));
            currentUserSubject.next(curUser);
            return curUser;
        }).catch(error => 
        {
            logout();
            window.location.reload(true);
            alert(error)
            return Promise.reject(error);
        })
}

function register(username, password, passwordConfirm, email) {
    return axios.post("http://localhost:8080/user/register", 
        JSON.stringify({ username, password, passwordConfirm, email }),
            {
                headers: { 'Content-Type': 'application/json' }
            }
        ).then( response => {
            localStorage.setItem('currentUser', JSON.stringify(response.data));
            currentUserSubject.next(response.data);
            return response.data;
        }).catch(error => 
        {
            logout();
            window.location.reload(true);
            alert(error)
            return Promise.reject(error);
        })
}
function logout() {
    localStorage.removeItem('currentUser');
    currentUserSubject.next(null);
}