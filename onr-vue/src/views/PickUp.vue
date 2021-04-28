<template>
  <div>
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="Enter Phone:">
        <el-input v-model="searchForm.phone" placeholder="Customer's Phone"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchPendingOrder">Search</el-button>
      </el-form-item>
    </el-form>

    <p v-if="!orders || orders.length === 0" style="text-align: center; color: #999">
      {{ emptyMessage }}
    </p>
    <el-row :gutter="16" v-if="orders && orders.length > 0">
      <el-col :sm="12" :md="8" :xl="6" v-for="order in this.orders" :key="order.id">
        <el-card>
          <div slot="header">
            <span style="color: #999">Customer: </span>
            {{ order.customer.name }}
            <el-divider direction="vertical"></el-divider>
            <span style="color: #999">Phone: </span>
            {{ order.customer.phone }}
          </div>
          <p v-for="orderFoods in order.foodsList" :key="orderFoods.id">
            <span v-if="orderFoods.food">
              {{ orderFoods.food.name + " × " + orderFoods.number }}
            </span>
          </p>
          <el-divider></el-divider>
          <p>
            {{ "Total: $" + order.orderPrice }}
          </p>
          <el-button type="primary" style="width: 100%" @click="pickUpOrder(order.id)">Pick Up</el-button>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "PickUp",
  data() {
    return {
      searchForm: { phone: "" },
      orders: [],
      emptyMessage: "Enter customer's phone number and search."
    }
  },
  methods: {
    searchPendingOrder() {
      if (this.searchForm.phone.length === 0)
        return this.$message.error({ message: "Please enter customer's phone number." });
      this.axios.get(`/orders?phone=${this.searchForm.phone}&status=0`).then(resp => {
        // console.log(resp);
        this.orders = resp;
        this.emptyMessage = "No result.";
      });
    },
    pickUpOrder(orderId) {
      this.axios.put(`/orders/pickup/${orderId}`).then(() => {
        this.orders = this.orders.filter(e => e.id !== orderId);
        this.emptyMessage = "Enter customer's phone number and search.";
      });
    }
  }
}
</script>

<style scoped>
.el-col {
  margin-bottom: 1rem;
}
</style>