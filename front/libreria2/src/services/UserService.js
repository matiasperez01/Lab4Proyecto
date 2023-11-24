import axios from 'axios'

export default class UserService {
    url = "http://localhost:8080/api";
    getAll(){
        return axios.get(this.url + "/users")
    }

    save(user){
        return axios.post(this.url + "/users", user)
    }

    delete(id){
        return axios.delete(this.url + "/users/"+id)
    }

}