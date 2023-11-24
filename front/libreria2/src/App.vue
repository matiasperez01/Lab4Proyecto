<template>
    <div id="app">
      <Menubar :model="items" />
      <UserCrud v-if="this.showUserCrud"/>
      <LibroCrud v-if="this.showLibroCrud"/>
      <PrestamoCrud v-if="this.showPrestamoCrud"/>
  
    </div>
  
  </template>
  

  <script>
  
  import UserCrud from './components/UserCrud.vue'
  import LibroCrud from './components/LibroCrud.vue'
  import PrestamoCrud from './components/PrestamoCrud.vue'
  
  export default {
  
    components: {
  
      UserCrud,
      LibroCrud,
      PrestamoCrud
  
    },
    data() {
      return{
        items: [
            {
              label: 'LIBRERIA',
              icon: 'pi pi-book',
              items: [
                  {
                  label: 'Libros',
                  icon: 'pi pi-book',
                  command: () => {
                    this.toggleLibroCrud();
                    }
                  },
                  {separator:true},
                  {
                    label: 'Prestamos',
                    icon: 'pi pi-money-bill',
                    command: (event) => {
                      this.togglePrestamoCrud();
                    }
                  },
                  {separator:true},
                  {
                    label: 'Usuarios',
                    icon: 'pi pi-users',
                    command: () => {
                      this.toggleUserCrud();
                    }
                  },
              ],
            },         
        ],
        showUserCrud: false,
        showLibroCrud: false,
        showPrestamoCrud: false,
      };
    },
    methods: {
      toggleUserCrud(){
        this.showUserCrud = !this.showUserCrud;
        this.showLibroCrud = false;
        this.showPrestamoCrud = false;
      },
      toggleLibroCrud(){
        this.showLibroCrud = !this.showLibroCrud;
        this.showUserCrud = false;
        this.showPrestamoCrud = false;
      },
      togglePrestamoCrud(){
        this.showPrestamoCrud = !this.showPrestamoCrud;
        this.showUserCrud = false;
        this.showLibroCrud = false;
      },
    },

  
  };
  
</script>
  
<style>
 
</style>