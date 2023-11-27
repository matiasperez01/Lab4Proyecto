<template>
    <div id="data-table">
        <Panel header="PRESTAMOS">
            <Menubar :model="items" />
        <DataTable v-model:selection="this.selectedPrestamo" :value="prestamos" selectionMode="single"  :paginator="true" :rows="8" responsiveLayout="scroll" dataKey="id_prestamo">
            <Column field="id_prestamo" header="ID"></Column>
            <Column field="id_book" header="ID Libro"></Column>    
            <Column field="fechaprestamo" header="fecha prestamo"></Column>    
            <Column field="fechadevolucion" header="fecha devolucion"></Column>    
            <Column field="nombre_prestatario" header="prestatario"></Column>    
        </DataTable>
        </Panel>
        <Dialog v-model:visible="displayModal" modal header="Crear Prestamo" :style="{ width: '50rem' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">  
            <span class="p-float-label">
                <select v-model="this.prestamo.id_book" style="display: flexbox; width: 259px; height: 50px;"> 
                    <option v-for="libro in this.libros" :value="libro.id_book">{{ libro.titulo }} - ID:{{ libro.id_book }}</option>
                </select>
            </span>
            <br>
            <span class="p-float-label">
                <Calendar v-model="prestamo.fechaprestamo" dateFormat="dd.mm.yy" />
                <label for="fechaprestamo">Fecha de prestamo</label>
            </span>
            <br>
            <span class="p-float-label">
                <Calendar v-model="prestamo.fechadevolucion" dateFormat="dd.mm.yy" />
                <label for="fechadevolucion">Fecha de devolucion</label>
            </span>
            <br>
            <span class="p-float-label">
                <InputText id="nombre_prestatario" v-model="prestamo.nombre_prestatario" />
                <label for="nombre_prestatario">Nombre prestatario</label>
            </span>
            
            <template #footer>
                <Button label="Cancelar" icon="pi pi-times" @click="this.closeModal" autofocus />
                <Button label="Guardar" icon="pi pi-check" @click="this.save" autofocus />
            </template>
        </Dialog>
    </div>
</template>
<script>
    import PrestamoService from '../services/PrestamoService';
    import LibroService from '../services/LibroService';

    export default {
        name : 'PrestamoCrud',
        data(){
            return{
                libros: null,
                libro: {
                    id_book: null,
                    titulo: null,
                    autor: null,
                    genero: null,
                    num_paginas: null,
                    sinopsis: null,
                },
                prestamos: null,
                prestamo: {
                    id_prestamo: null,
                    id_book: null,
                    fecha_prestamo: null,
                    fecha_devolucion: null,
                    nombre_prestatario: null,
                },
                selectedPrestamo: {
                    id_prestamo: null,
                    id_book: null,
                    fecha_prestamo: null,
                    fecha_devolucion: null,
                    nombre_prestatario: null,
                },
                selectedBook: {
                    id_book: null,
                    titulo: null,
                    autor: null,
                    genero: null,
                    num_paginas: null,
                    sinopsis: null,
                },
                items: [
                    {
                        label: ' Nuevo',
                        icon: 'pi pi-plus',
                        command : () => {
                            this.showSaveModal();
                        }
                    },
                    {
                        label: ' Editar',
                        icon: 'pi pi-wrench',
                        command : () => {
                            this.showEditModal();
                        }
                    },
                    {
                        label: ' Eliminar',
                        icon: 'pi pi-trash',
                        command: () => {
                            this.delete();
                        }
                    },
                    {
                        label: ' Refrescar',
                        icon: 'pi pi-refresh',
                        command: ()=> {
                            this.getAll();
                        }
                    }
                ],
                displayModal: false
            }
        },
        libroService : null,
        prestamoService : null,
        created(){
            this.prestamoService = new PrestamoService();
            this.libroService = new LibroService();
        },
        mounted(){
            this.getAll();
            this.getAllBooks();
        },
        methods: {
            showSaveModal(){
                this.displayModal = true;
            },
            showEditModal(){
                if(this.selectedPrestamo.id_prestamo==null){
                    alert("Debe seleccionar un prestamo");
                }
                else{
                this.prestamo = this.selectedPrestamo;
                this.displayModal = true;
                }
            },
            getAllBooks(){
                this.libroService.getAll().then(data=>{
                    this.libros = data.data;
                });
            },
            getAll(){
                this.prestamoService.getAll().then(data=> {
                this.prestamos = data.data;
                });
            },
            delete(){
                if(this.selectedPrestamo.id_prestamo==null){
                    alert("Debe seleccionar un prestamo");
                }else{
                    if(confirm("Esta seguro que desea eliminar el prestamo?")){
                        this.prestamoService.delete(this.selectedPrestamo.id_prestamo).then(data =>{
                            if(data.status == 200){
                                this.getAll();
                            };
                        });    
                    }
                }
            },
            save(){
                if(this.prestamo.id_book==null || this.prestamo.fecha_prestamo==null || this.prestamo.fecha_devolucion==null || this.prestamo.nombre_prestatario==null ){
                    alert("Debe completar todos los campos");
                }else{
                    this.prestamoService.save(this.prestamo).then(data => {
                        if(data.status == 200){
                            this.displayModal = false;
                            this.prestamo = {
                                id_prestamo: null,
                                id_book: null,
                                fecha_prestamo: null,
                                fecha_devolucion: null,
                                nombre_prestatario: null,
                            };
                            this.getAll();
                        }
                    })
                }
            },
            closeModal(){
                this.displayModal = false;
            }
        }
    }
</script>
<style>
#data-table{
    margin: 0 auto;
    width: 80%;
}
</style>
