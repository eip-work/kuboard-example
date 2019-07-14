<template>
  <div>
    <div style="text-align: right;">
      <el-button type="primary" @click="dialogVisible = true">创 建</el-button>
    </div>
    <div style="text-align: left;">
      <el-dialog title="创建书目" :visible.sync="dialogVisible" width="60%">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="书名" required>
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="作者" required>
            <el-input v-model="form.author"></el-input>
          </el-form-item>
          <el-form-item label="SN" required>
            <el-input v-model="form.sn"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false" :loading="creating">取 消</el-button>
          <el-button type="primary" @click="create" :loading="creating">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      dialogVisible: false,
      form: {},
      creating: false
    }
  },
  methods: {
    create () {
      this.creating = true
      this.$svc_example.post('/book/create', this.form).then(resp => {
        this.creating = false
        this.form = {}
        this.dialogVisible = false
        this.$message.success(`创建成功: ${resp.data}`)
        this.$emit('success')
      }).catch(e => {
        this.creating = false
        this.$message.error(`创建失败: ${e}`)
      })
    }
  }
}
</script>

<style scoped>

</style>
