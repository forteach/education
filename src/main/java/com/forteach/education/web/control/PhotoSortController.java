package com.forteach.education.web.control;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-27 08:53
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(path = "/photoSort", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "课件图册操作", tags = {"课件图册操作"})
public class PhotoSortController {

//    private final PhotoSortService photoSortService;
//
//    @Autowired
//    public PhotoSortController(PhotoSortService photoSortService) {
//        this.photoSortService = photoSortService;
//    }
//
//    @PostMapping("/save")
//    @ApiOperation(value = "保存图册信息", notes = "保存图册及其描述信息")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "sortImgName", value = "分类名称", dataType = "string", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "sortImgType", value = "展示方式", dataType = "int", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "imgPassword", value = "查看密码", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "topPicSrc", value = "封面图片路径", required = true, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "courceId", value = "科目ID", required = true, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "photos", value = "图片集", dataTypeClass = Photos.class, required = true, paramType = "from")
//    })
//    public WebResult save(@Valid @NotNull(message = "图册信息不为空") @ApiParam(value = "保存图册", name = "photoSortVo", required = true) @RequestBody PhotoSortVo photoSortVo){
//        return WebResult.okResult(photoSortService.save(photoSortVo));
//    }
//
//    @PostMapping("/findById")
////    @JsonView(View.SummaryExtend.class)
//    @ApiOperation(value = "查询图册信息", notes = "根据图册编号查询对应的图册信息")
//    @ApiImplicitParam(name = "sortImgId", value = "图册ID", dataType = "string", paramType = "query", required = true)
//    public WebResult findById(@Valid @NotBlank(message = "图册ID不为空") @ApiParam(value = "图册id", type = "string", required = true, name = "sortImgId") @RequestBody String sortImgId){
//        return WebResult.okResult(photoSortService.findById(String.valueOf(JSONObject.parseObject(sortImgId).getString("sortImgId"))));
//    }
//
//    @ApiOperation(value = "删除图册信息", notes = "根据图册 ID 删除数据库保存的图册信息")
//    @PostMapping(value = "/deleteById")
//    @ApiImplicitParam(name = "sortImgId", value = "图册ID", dataType = "string", paramType = "query", required = true)
//    public WebResult deleteById(@Valid @NotBlank(message = "图册ID不为空") @ApiParam(value = "图册id", type = "string", required = true, name = "sortImgId") @RequestBody String sortImgId){
//        photoSortService.deleteById(String.valueOf(JSONObject.parseObject(sortImgId).getString("sortImgId")));
//        return WebResult.okResult();
//    }
//
//    @PostMapping(value = "/edit")
////    @JsonView(View.SummaryExtend.class)
//    @ApiOperation(value = "修改图册信息", notes = "修改图册信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "sortImgId", value = "图册ID", dataType = "string", paramType = "query", required = true),
//            @ApiImplicitParam(name = "sortImgName", value = "分类名称", dataType = "string", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "sortImgType", value = "展示方式", dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "imgPassword", value = "查看密码", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "topPicSrc", value = "封面图片路径", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "courceId", value = "科目ID", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "photos", value = "图片集", dataTypeClass = Photos.class, paramType = "from")
//    })
//    public WebResult edit(@Valid @NotNull(message = "图册信息不为空") @ApiParam(value = "修改图册", name = "photoSort", required = true) @RequestBody PhotoSort photoSort){
//        return WebResult.okResult(photoSortService.edit(photoSort));
//    }
}
