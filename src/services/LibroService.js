import axios from 'axios'

export default class UserService {
    url = "http://localhost:8080/api";
    getAll(){
        return axios.get(this.url + "/libros")
    }
    getAllBooks(){
        return axios.get(this.url + "/libros")
    }

    save(libro){
        return axios.post(this.url + "/libros", libro)
    }

    delete(id){
        return axios.delete(this.url + "/libros", id)
    }

}