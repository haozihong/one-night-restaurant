<template>
  <div>
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="Enter Phone:">
        <el-input v-model="searchForm.phone" placeholder="9998887777"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchCustomerByPhone">Search</el-button>
        <el-button type="success" @click="newCustomerFormVisible = true">New Customer</el-button>
      </el-form-item>
    </el-form>

    <p>
      <span style="color: #999">Customer: </span>
      {{ currentCustomer.name.length > 0 ? currentCustomer.name : "N/A" }}
      <el-divider direction="vertical"></el-divider>
      <span style="color: #999">Phone: </span>
      {{ currentCustomer.phone.length > 0 ? currentCustomer.phone : "N/A" }}
    </p>

    <el-row :gutter="20">
      <el-col :md="18">
        <el-card shadow="hover">
          <el-table :data="foods" :empty-text="tableEmptyText">
            <el-table-column prop="id" width="30"/>
            <el-table-column prop="name" label="Name"/>
            <el-table-column prop="price" label="Unit Price ($)"/>
            <el-table-column prop="totalPrice" label="Total ($)"/>
            <el-table-column prop="number" label="Amount" width="100"/>
            <el-table-column width="150">
              <template slot-scope="scope">
                <el-button
                    size="small"
                    type="success"
                    icon="el-icon-plus"
                    plain
                    circle
                    @click="changeFoodNum(scope.row, 1)"></el-button>
                <el-button
                    size="small"
                    type="danger"
                    icon="el-icon-minus"
                    plain
                    circle
                    @click="changeFoodNum(scope.row, -1)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>

      <el-col :md="6">
        <el-card shadow="hover">
          <div slot="header">
            Summary
          </div>
          <div v-if="foodsNeeded.length === 0" style="color: #999">
            Empty Cart
          </div>
          <div v-for="food in this.foodsNeeded" :key="food.id">
            {{ food.name + " × " + food.number }}
          </div>
          <hr v-if="foodsNeeded.length > 0">
          <div v-if="foodsNeeded.length > 0">
            {{ "Total: $" + this.foods.reduce((a, b) => a + b.totalPrice, 0).toFixed(2) }}
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-button type="primary" style="width: 100%" @click="submitOrder" :loading="submitLoading">Submit Order</el-button>
    </el-row>

    <!--New customer form-->
    <el-dialog title="Add New Customer" :visible.sync="newCustomerFormVisible">
      <el-form :model="newCustomerForm" ref="newCustomerForm" label-width="5rem">
        <el-form-item label="Name">
          <el-input v-model="newCustomerForm.name"></el-input>
        </el-form-item>
        <el-form-item label="Phone">
          <el-input type="tel" v-model="newCustomerForm.phone"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addCustomer">Add</el-button>
        <el-button @click="newCustomerFormVisible = false">Cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "NewOrder",
  data() {
    return {
      searchForm: {
        phone: ''
      },
      newCustomerForm: {
        name: '',
        phone: ''
      },
      currentCustomer: {
        name: '',
        phone: ''
      },
      newCustomerFormVisible: false,
      foods: [],
      foodsNeeded: [],
      tableEmptyText: "No Data",
      foodsNum: {},
      submitLoading: false
    }
  },
  computed: {
  },
  mounted() {
    this.initAllFoods();
  },
  methods: {
    initAllFoods() {
      this.tableEmptyText = "Fetching Data...";
      this.axios.get("/foods").then(resp => {
        // console.log(resp);
        if (resp) {
          this.foods = resp;
          this.foods.forEach(e => e.number = e.totalPrice = 0);
        }
        this.tableEmptyText = "No Data";
      });
    },
    searchCustomerByPhone() {
      this.axios.get(`/customers?phone=${this.searchForm.phone}`).then(resp => {
        // console.log(resp);
        if (resp && resp[0] != null) {
          this.currentCustomer = resp[0];
        }
      });
    },
    addCustomer() {
      // console.log(this.newCustomerForm);
      this.axios.post(`/customers`, this.newCustomerForm).then(resp => {
        if (resp) {
          this.newCustomerFormVisible = false;
          this.currentCustomer = resp.obj;
        }
      });
    },
    changeFoodNum(food, delta) {
      food.number += delta;
      if (food.number < 0) food.number = 0;
      food.totalPrice = food.price * food.number;
      this.foodsNeeded = this.foods.filter(e => e.number > 0);
      this.foods.splice(1, 0);  // trigger the table to update view
    },
    submitOrder() {
      if (this.currentCustomer.name.length === 0)
        return this.$message.error({ message: "Please set a customer." });
      if (this.foodsNeeded.length === 0)
        return this.$message.error({ message: "Empty order." });
      this.submitLoading = true;
      let order = {
        customerId: this.currentCustomer.id,
        foodsList: []
      };
      this.foodsNeeded.forEach(e => {
        order.foodsList.push({ foodId: e.id, number: e.number });
      });
      // console.log(order);
      this.axios.post(`/orders`, order).then(resp => {
        // console.log(resp);
        if (resp) {
          this.foods.forEach(e => e.number = e.totalPrice = 0);
          this.foodsNeeded = [];
          this.currentCustomer = { name: '', phone: '' };
        }
        this.submitLoading = false;
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