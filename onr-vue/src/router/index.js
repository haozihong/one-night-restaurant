import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import NewOrder from "@/views/NewOrder";
import PickUp from "@/views/PickUp";
import AllOrders from "@/views/AllOrders";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '/new-order',
        name: 'NewOrder',
        component: NewOrder
      },
      {
        path: '/pick-up',
        name: 'PickUp',
        component: PickUp
      },
      {
        path: '/all-orders',
        name: 'AllOrders',
        component: AllOrders
      }
    ]
  },
  { path: '*', component: Home }
]

const router = new VueRouter({
  routes
})

export default router
