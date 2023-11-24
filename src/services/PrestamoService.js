import axios from 'axios'

export default class PrestamoService {
    url = "http://localhost:8080/api";
    getAll(){
        return axios.get(this.url + "/prestamos")
    }

    save(prestamo){
        return axios.post(this.url + "/prestamos", prestamo)
    }

    delete(id){
        return axios.delete(this.url + "/prestamos/"+id)
    }

}