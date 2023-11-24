<template>
    <div id="data-table">
        <Panel header="LIBROS">
            <Menubar :model="items" />
        <DataTable v-model:selection="this.selectedLibro" :value="this.librosTable" selectionMode="single"  :paginator="true" :rows="8" responsiveLayout="scroll" dataKey="id_book">
            <Column field="id_book" header="ID"></Column>
            <Column field="titulo" header="Titulo"></Column>    
            <Column field="autor" header="Autor"></Column>    
            <Column field="genero" header="Genero"></Column>    
            <Column field="num_paginas" header="Cant. paginas"></Column>    
            <Column field="sinopsis" header="Sinopsis"></Column>    
        </DataTable>
        </Panel>
        <Dialog v-model:visible="displayModal" modal header="Crear Libro" :style="{ width: '50rem' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">
            <span class="p-float-label">
                <InputText id="titulo" v-model="libro.titulo" />
                <label for="titulo">Titulo</label>
            </span>
            <br>
            <span class="p-float-label">
                <InputText id="autor" v-model="libro.autor" />
                <label for="autor">Autor</label>
            </span>
            <br>
            <span class="p-float-label">
                <InputText id="genero" v-model="libro.genero" />
                <label for="genero">Genero</label>
            </span>
            <br>
            <span class="p-float-label">
                <InputText id="num_paginas" v-model="libro.num_paginas" />
                <label for="num_paginas">Cant. de paginas</label>
            </span>
            <br>
            <span class="p-float-label">
                <Textarea v-model="libro.sinopsis" :autoResize="true" rows="5" cols="30" />
                <label for="sinopsis">Sinopsis</label>
            </span>
            <br>
            <template #footer>
                <Button label="Cancelar" icon="pi pi-times" @click="this.closeModal" autofocus />
                <Button label="Guardar" icon="pi pi-check" @click="this.save" autofocus />
            </template>
        </Dialog>

        <Dialog v-model:visible="displayAutor" modal header="Buscar libros por Autor" :style="{ width: '50rem' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">
            
            <span class="p-float-label">
                <select v-model="this.filtroAutor" style="display: flexbox; width: 259px; height: 50px;"> 
                    <option  v-for="libro in this.libros" :value="libro.autor"> {{ libro.autor }} </option>
                </select>
            </span>
            
            <template #footer>
                <Button label="Cancelar" icon="pi pi-times"  autofocus />
                <Button label="Buscar" icon="pi pi-check" @click="updateTableAutor" autofocus />
            </template>
        </Dialog>

        <Dialog v-model:visible="displayGenero" modal header="Buscar libros por Genero" :style="{ width: '50rem' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">
            
            <span class="p-float-label">
                <select v-model="this.filtroGenero" style="display: flexbox; width: 259px; height: 50px;"> 
                    <option  v-for="libro in this.libros" :value="libro.genero"> {{ libro.genero }} </option>
                </select>
            </span>
            
            <template #footer>
                <Button label="Cancelar" icon="pi pi-times"  autofocus />
                <Button label="Buscar" icon="pi pi-check" @click="updateTableGenero" autofocus />
            </template>
        </Dialog>

        <Dialog v-model:visible="displayTitulo" modal header="Buscar libros por Titulo" :style="{ width: '50rem' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">
            
            <span class="p-float-label">
                <InputText id="filtroTitulo" v-model="filtroTitulo" />
                <label for="filtroTitulo">Titulo</label>
            </span>
            
            <template #footer>
                <Button label="Cancelar" icon="pi pi-times"  autofocus />
                <Button label="Buscar" icon="pi pi-check" @click="updateTableTitulo" autofocus />
            </template>
        </Dialog>
    </div>
</template>
<script>
    import LibroService from '../services/LibroService';
    export default {
        name : 'LibroCrud',
        data(){
            return{
                libros: null,
                libro: {
                    id_book: null,
                    titulo: null,
                    autor: null,
                    genero: null,
                    num_paginas: null,
                    sinopsis: null
                },
                librosTable: [],
                selectedLibro: {
                    id_book: null,
                    titulo: null,
                    autor: null,
                    genero: null,
                    num_paginas: null,
                    sinopsis: null
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
                    },
                    {
                        label: 'Filtrar',
                        icon: 'pi pi-filter-fill',
                        items: [
                            {
                                label: 'Por autor',
                                command: ()=> {
                                    this.showBuscarAutor();
                                }
                            },
                            {
                                label: 'Por titulo',
                                command: ()=> {
                                    this.showBuscarTitulo();
                                }
                            },
                            {
                                label: 'Por genero',
                                command: ()=>{
                                    this.showBuscarGenero();
                                }
                            },
                        ]
                    },
                ],
                displayModal: false,
                displayAutor: false,
                displayGenero: false,
                displayTitulo: false,
                filtroTitulo: null,
                filtroAutor: null,
                filtroGenero: null,
            }
        },
        libroService : null,
        created(){
            this.libroService = new LibroService();
        },
        mounted(){
            this.getAll();
        },
        methods: {
            showSaveModal(){
                this.displayModal = true;
            },
            showEditModal(){
                if(this.selectedLibro.id_book==null){
                    alert("Debe seleccionar un libro");
                }else{
                    this.displayModal = true;
                    this.libro = this.selectedLibro;
                }
            },
            getAll(){
                this.libroService.getAll().then(data=> {
                this.libros = data.data;
                this.librosTable = data.data;
                });
            },
            delete(){
                if(this.selectedLibro.id_book==null){
                    alert("Debe seleccionar un libro");
                }else{
                    if(confirm("Esta seguro que desea eliminar al usuario?")){
                        this.libroService.delete(this.selectedLibro.id_book).then(data =>{
                            if(data.status == 200){
                                this.getAll();
                            };
                        });    
                    }
                }   
            },
            save(){
                if(this.libro.autor==null || this.libro.titulo==null || this.libro.genero==null || this.libro.num_paginas==null || this.libro.sinopsis==null){
                    alert("Debe completar todos los campos");
                }else{
                this.libroService.save(this.libro).then(data => {
                    if(data.status == 200){
                        this.displayModal = false;
                        this.libro = {
                            titulo: null,
                            autor: null,
                            genero: null,
                            num_paginas: null,
                            sinopsis: null,
                        };
                        this.getAll();
                    }
                })
                }
            },
            closeModal(){
                this.displayModal = false;
            },
            showBuscarTitulo(){
                this.displayTitulo = true;
            },
            showBuscarAutor(){
                this.displayAutor = true;
            },
            showBuscarGenero(){
                this.displayGenero = true;
            },
            updateTableTitulo(){
                if(this.filtroTitulo==null){
                    alert("Debe escribir un titulo");
                }else{
                if(this.filtroTitulo!=null){
                    this.librosTable = this.libros.filter((libro) => libro.titulo == this.filtroTitulo);
                    this.displayTitulo = false;
                    if(this.librosTable.length==0){
                        alert("No se encontraron libros con ese titulo.");
                        this.librosTable = this.libros;
                    }
                }else{
                    this.librosTable = this.libros;
                }
                }
            },
            updateTableAutor(){               
                if(this.filtroAutor){
                    this.librosTable = this.libros.filter((libro) => libro.autor == this.filtroAutor);
                    this.displayAutor = false;
                }else{
                    this.librosTable = this.libros;
                }
            },
            updateTableGenero(){  
                if(this.filtroGenero){
                    this.librosTable = this.libros.filter((libro) => libro.genero == this.filtroGenero);
                    this.displayGenero = false;
                }else{
                    this.librosTable = this.libros;
                }
            },
    }
}
</script>
<style>
#data-table{
    margin: 0 auto;
    width: 80%;
}
</style>