// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    info:[]
  },
  goToInfo:function (event) {
    var name = event.currentTarget.dataset.name;
    //console.log(name);
    wx.navigateTo({
      url: '../info/info?name='+name,
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/Info/JsonServlet',
      data:{
        name:''
      },
      method: 'GET',
      success: function (res) {
        that.setData({
          info:res.data[0]
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  }
})