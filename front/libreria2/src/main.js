import './assets/main.css'
import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import DataTable from "primevue/datatable"
import Column from "primevue/column"
import Panel from "primevue/panel"
import Menubar from 'primevue/menubar';
import 'primevue/resources/themes/lara-light-teal/theme.css'
import 'primeicons/primeicons.css'
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import Textarea from 'primevue/textarea';
import Calendar from 'primevue/calendar';


const app = createApp(App);
app.use(PrimeVue);
app.component('DataTable', DataTable);
app.component('Column', Column);
app.component('Panel', Panel);
app.component('Menubar', Menubar);
app.component('Dialog', Dialog);
app.component('InputText', InputText);
app.component('Button', Button);
app.component('Textarea', Textarea);
app.component('Calendar', Calendar);

app.mount('#app');



