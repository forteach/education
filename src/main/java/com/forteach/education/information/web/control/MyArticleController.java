package com.forteach.education.information.web.control;


//
//@RestController
//@RequestMapping(path = "/myArticle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@Api(value = "我的资讯资料", tags = {"我的资讯资料（收藏、发布）"})
public class MyArticleController {

//	private final MyArticleService myArticleService;
//
//	@Autowired
//	public MyArticleController(MyArticleService myArticleService) {
//		this.myArticleService = myArticleService;
//	}

//	@PostMapping("/save")
//	public WebResult saveMyArticle(SaveMyArticleRequest request) {
//		String articleId = request.getArticleId();
//		String userId = request.getUserId();
//		String tagType=request.getTagType();//发布者编号
//		//判断参数
//		MyAssert.isNull(articleId, DefineCode.ERR0010,"编号不能为空");
//		MyAssert.isNull(userId,DefineCode.ERR0010,"用户编号不能为空");
//
//		MyArticle  myArticle=myArticleService.setMyArticle("",userId,articleId, tagType);
//		return WebResult.okResult(myArticleService.save(myArticle));
//	}
//
//	@PostMapping("/findUserIdtagType")
//	public WebResult findByUserIdtagType(@RequestBody String userId, int tagType) {
// 		return WebResult.okResult(myArticleService.findByUserIdtagType(userId,tagType));
//	}
//
//	/**
//	 * 根据Id删除资讯
//	 * @param id
//	 * @return
//	 */
//	@PostMapping("/delId")
//	public WebResult deleteId(String id) {
//		return WebResult.okResult(myArticleService.deleteMyArticleById(id));
//	}
//
//	/**
//	 * 删除点赞记录
//	 * @param req
//	 * @return
//	 */
//	@PostMapping("/delGood")
//	public WebResult delGood(@RequestBody DeleteMyArticleRequest req) {
//		return WebResult.okResult(myArticleService.deleteMyArticle(req.getArticleId(),req.getUserId(),myArticleService.GOOD));
//	}
//
//	/**
//	 * 删除收藏记录
//	 * @param req
//	 * @return
//	 */
//	@PostMapping("/delCollect")
//	public WebResult deleteMyArticle(@RequestBody DeleteMyArticleRequest req) {
//		return WebResult.okResult(myArticleService.deleteMyArticle(req.getArticleId(),req.getUserId(),myArticleService.SHOUCANG));
//	}
}