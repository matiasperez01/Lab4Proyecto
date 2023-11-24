<template>
    <div id="data-table">
        <Panel header="USUARIOS">
            <Menubar :model="items" />
        <DataTable v-model:selection="this.selectedUser" :value="users" selectionMode="single"  :paginator="true" :rows="8" responsiveLayout="scroll" dataKey="id_usuario">
            <Column field="id_usuario" header="ID"></Column>
            <Column field="nombre" header="Nombre"></Column>    
        </DataTable>
        </Panel>
        <Dialog v-model:visible="displayModal" modal header="Crear Usuario" :style="{ width: '50rem' }" :breakpoints="{ '1199px': '75vw', '575px': '90vw' }">
            <span class="p-float-label">
                <InputText id="nombre" v-model="user.nombre"/>
                <label for="nombre">Nombre de Usuario</label>
            </span>
            <template #footer>
                <Button label="Cancelar" icon="pi pi-times" @click="this.closeModal" autofocus />
                <Button label="Guardar" icon="pi pi-check" v-if="this.validate" @click="this.save" autofocus />
            </template>
        </Dialog>
    </div>
</template>
<script>
    import UserService from '../services/UserService';
    export default {
        name : 'UserCrud',
        data(){
            return{
                users: null,
                user: {
                    id_usuario: null,
                    nombre: null
                },
                selectedUser: {
                    id_usuario: null,
                    nombre: null
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
                displayModal: false,
                validate: true,
            }
        },
        userService : null,
        created(){
            this.userService = new UserService();
        },
        mounted(){
            this.getAll();
        },
        methods: {
            showSaveModal(){
                this.displayModal = true;
            },
            showEditModal(){
                if(this.selectedUser.id_usuario==null){
                    alert("No selecciono un usuario");
                    this.displayModal = false;
                }else{
                    this.user = this.selectedUser;
                    this.displayModal = true;
                }
            },            
            getAll(){
                this.userService.getAll().then(data=> {
                this.users = data.data;
                });
            },
            delete(){
                if(this.selectedUser.id_usuario==null){
                    alert("No selecciono un usuario");
                    this.displayModal = false;
                }else{
                    if(confirm("Esta seguro que desea eliminar al usuario?")){
                        this.userService.delete(this.selectedUser.id_usuario).then(data =>{
                            if(data.status == 200){
                                this.getAll();
                            };
                        });    
                    }
                }
            },
            save(){
                if(this.user.nombre==null){
                    alert("Debe completar los campos");
                }else{
                    this.userService.save(this.user).then(data => {
                        if(data.status == 200){
                            this.displayModal = false;
                            this.user = {
                                nombre: null
                            };
                            this.getAll();
                        }
                    })    
                }
            },
            closeModal(){
                this.displayModal = false;
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