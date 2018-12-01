package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.AudioDatum;
import com.forteach.education.filter.View;
import com.forteach.education.service.AudioDatumService;
import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 21:22
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(path = "/audioDatum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "音频资料操作", tags = {"音频资源接口"})
public class AudioDatumController {
    private final AudioDatumService audioDatumService;

    @Autowired
    public AudioDatumController(AudioDatumService audioDatumService) {
        this.audioDatumService = audioDatumService;
    }

    @ApiOperation(value = "保存音频信息", notes = "保存音频资源链接信息")
    @PostMapping("/save")
    @JsonView(View.SummaryExtend.class)
    public WebResult save(@Valid @ApiParam(name = "audioDatum", value = "音频资料对象", required = true) @RequestBody AudioDatum audioDatum){
        return WebResult.okResult(audioDatumService.save(audioDatum));
    }

    @ApiOperation(value = "修改音频信息", notes = "修改资源信息")
    @PostMapping("/edit")
    @JsonView(View.SummaryExtend.class)
    public WebResult edit(@Valid @ApiParam(name = "audioDatum", value = "音频资料对象", required = true) @RequestBody AudioDatum audioDatum){
        return WebResult.okResult(audioDatumService.edit(audioDatum));
    }

    @ApiOperation(value = "删除音频信息", notes = "删除资源对象(物理删除)")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(name = "audioDatum", value = "删除资源对象", required = true) @RequestBody AudioDatum audioDatum){
        audioDatumService.delete(audioDatum);
        return WebResult.okResult();
    }

    @ApiOperation(value = "删除音频信息", notes = "根据音频资源ID 删除资源信息(物理删除)")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "audioId", value = "根据资源ID 删除对应资源信息", required = true) @RequestBody String audioId){
        audioDatumService.deleteById(String.valueOf(JSONObject.parseObject(audioId).get("AudioId")));
        return WebResult.okResult();
    }

    @PostMapping("/getAudioByAudioId")
    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "查询音频信息", notes = "根据音频资源ID查询音频资源信息")
    public WebResult getAudioByAudioId(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "audioId", value = "根据资源ID 删除对应资源信息", required = true) @RequestBody String audioId){
        return WebResult.okResult(audioDatumService.getAudioDatumById(String.valueOf(JSONObject.parseObject(audioId).get("AudioId"))));
    }

    @ApiOperation(value = "分页信息", notes = "分页查询音频资源信息")
    @PostMapping("/findAll")
    @JsonView(View.SummaryExtend.class)
    public WebResult findAll(@Valid @ApiParam(name = "sortVo", value = "分页查询势派资源信息", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(audioDatumService.findAll(sortVo));
    }

    /**
     * 通过音频资源 ID 逻辑删除音频资源信息
     * @param audioId
     * @return
     */
    @ApiOperation(value = "使其无效", notes = "删除音频信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    public WebResult deleteIsValidById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "audioId", value = "根据资源ID 逻辑删除对应资源信息", required = true) @RequestBody String audioId){
        audioDatumService.deleteIsValidById(audioId);
        return WebResult.okResult();
    }

    @ApiOperation(value = "根据章节ID查询音频信息", notes = "根据章节ID查询有效音频信息")
    @PostMapping("/findByChapterId")
    @JsonView(View.SummaryExtend.class)
    public WebResult findByChapterId(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "chapterId", value = "根据章节 ID 查询音频信息", required = true) @RequestBody String chapterId){
        return WebResult.okResult(audioDatumService.findByChapterId(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId"))));
    }
}
