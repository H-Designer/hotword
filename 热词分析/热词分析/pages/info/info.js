// pages/info/info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:"",
    fullInfo:{},
    oneInfo:[],
    guessLike:[]
  },
  goToNews:function(res){
    wx.navigateTo({
      url: '../news/news?url='+res.currentTarget.dataset.url,
    })
  },
  guess:function(res){
    var that = this;
    var name = res.currentTarget.dataset.name;
    //console.log(name)
    wx.redirectTo({
      url: '../info/info?name=' + name,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //console.log(options)
    var that = this;
    wx.request({
      url: 'http://localhost:8080/Info/JsonServlet',
      data:{
        name:options.name
      },
      success:function(res){
        that.setData({
          fullInfo:res.data[0],
          oneInfo: res.data[0].information.split(";")
        }),
        wx.request({
          url: 'http://localhost:8080/Info/TypeServlet',
          data:{
            type:res.data[0].type
          },
          success:function(res){
            that.setData({
              guessLike:res.data[0]
            })
          }
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})