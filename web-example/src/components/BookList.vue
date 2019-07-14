<template>
  <div class="hello">
    <h2>Book Example</h2>
    <div style="padding: 0 30px;">
      <BookCreate @success="refresh"></BookCreate>
      <el-table :data="bookList.list" style="width: 100%; margin-bottom: 10px;" stripe>
        <el-table-column prop="id" label="id" width="180">
        </el-table-column>
        <el-table-column prop="name" label="书名">
        </el-table-column>
        <el-table-column prop="author" label="作者">
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间">
          <template slot-scope="scope">
            {{format(scope.row.createTime, 'YYYY-MM-DD HH:mm:ss')}}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button @click="deleteBook(scope.row)" type="text">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background layout="prev, pager, next" :total="bookList.total" @current-change="currentChange"></el-pagination>
    </div>
  </div>
</template>

<script>
import {format} from 'date-fns'
import BookCreate from './BookCreate'

export default {
  name: 'BookList',
  data () {
    return {
      bookList: {
        pageSize: 10,
        pageNum: 1,
        total: 0,
        list: []
      },
      loading: false
    }
  },
  mounted () {
    this.refresh()
  },
  components: {BookCreate},
  methods: {
    format,
    refresh () {      
      this.loading = true
      this.$svc_example.get(`/book/list`, {params: {pageSize: this.bookList.pageSize, pageNum: this.bookList.pageNum}}).then(resp => {
        this.bookList = resp.data
        this.loading = false
      }).catch(e => {
        this.loading = false
        this.$message.error('加载失败: ' + e)
      })
    },
    deleteBook(book) {
      this.$svc_example.delete(`/book/${book.id}/delete`).then(resp => {
        this.$message.success('成功删除: ' + book.id)
        this.refresh()
      }).catch(e => {
        this.$message.error('删除失败: ' + book.id + '  ' + e)
      })
    },
    currentChange (pageNum) {
      this.bookList.pageNum = pageNum
      this.refresh()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
