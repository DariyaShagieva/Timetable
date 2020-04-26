import App from './app';
import Login from './components/index'

const routes = [
    { path: '/', component: App },
    { path: '/login', component: Login }
  ]
  
  // 3. Create the router instance and pass the `routes` option
  // You can pass in additional options here, but let's
  // keep it simple for now.
  const router = new VueRouter({
    routes // short for `routes: routes`
  })