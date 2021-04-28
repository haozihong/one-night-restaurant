<template>
  <div>
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="Enter Phone:">
        <el-input v-model="searchForm.phone" placeholder="9998887777"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchCustomerByPhone">Search</el-button>
        <el-button type="primary" @click="newCustomerFormVisible = true">New Customer</el-button>
      </el-form-item>
    </el-form>

    <div>
      Customer: <span>{{ currentCustomer.name }}</span>
      Phone: <span>{{ currentCustomer.phone }}</span>
    </div>

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
    }
  },
  methods: {
    searchCustomerByPhone() {
      this.axios.get(`/customers?phone=${this.searchForm.phone}`).then(resp => {
        console.log(resp);
        if (resp && resp[0] != null) {
          this.currentCustomer = resp[0];
        }
      });
    },
    addCustomer() {
      console.log(this.newCustomerForm);
      this.axios.post(`/customers`, this.newCustomerForm).then(resp => {
        if (resp) {
          this.newCustomerFormVisible = false;
          this.currentCustomer = this.newCustomerForm;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>