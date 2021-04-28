<template>
  <div class="home">
    <el-container>
      <el-header id="home-header">
        <span class="title">MSI Bistro</span>
        <el-menu router mode="horizontal" class="hidden-xs-only">
          <el-menu-item index="/new-order">New Order</el-menu-item>
          <el-menu-item index="/pick-up">Pick Up</el-menu-item>
          <el-menu-item index="/all-orders">All Orders</el-menu-item>
        </el-menu>
        <span style="margin-left: auto; align-self: center">
            <i class="hidden-sm-and-up el-icon-s-fold"
               style="color: #666; cursor:pointer; font-size: 2rem"
               @click="showDrawer = true"></i>
        </span>
      </el-header>

      <el-main>
        <div class="homeWelcome" v-if="this.$router.currentRoute.path==='/'">
          Welcome to One Night Restaurant!
        </div>
        <router-view class="homeRouterView"/>
      </el-main>
    </el-container>

    <el-drawer title="ONR" :visible.sync="showDrawer" size="40%">
      <el-menu router>
        <el-menu-item index="/new-order" @click="showDrawer = false">New Order</el-menu-item>
        <el-menu-item index="/pick-up" @click="showDrawer = false">Pick Up</el-menu-item>
        <el-menu-item index="/all-orders" @click="showDrawer = false">All Orders</el-menu-item>
      </el-menu>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data: () => ({
    showDrawer: false
  })
}
</script>

<style>
.homeWelcome {
  text-align: center;
  font-size: 30px;
  color: #3FABD6;
  padding-top: 3rem;
}

#home-header {
  background-color: #fff;
  display: flex;
  border-bottom: solid 1px #e6e6e6;
}

#home-header .title {
  font-size: 1.25rem;
  align-self: center;
}
</style>