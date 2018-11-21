package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.AudioDatum;
import com.forteach.education.service.AudioDatumService;
import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 21:22
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/audioDatum")
@Api(value = "音频资料操作", tags = {"音频资源接口"})
public class AudioDatumController {
    @Autowired
    private AudioDatumService audioDatumService;

    @ApiOperation(value = "audioDatum", notes = "保存音频资源链接信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(value = "audioDatum", name = "音频资料对象", required = true) @RequestBody AudioDatum audioDatum){
        return WebResult.okResult(audioDatumService.save(audioDatum));
    }

    @ApiOperation(value = "audioDatum", notes = "修改资源信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(value = "audioDatum", name = "音频资料对象", required = true) @RequestBody AudioDatum audioDatum){
        return WebResult.okResult(audioDatumService.edit(audioDatum));
    }

    @ApiOperation(value = "delete", notes = "删除资源对象")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(value = "audioDatum", name = "删除资源对象", required = true) @RequestBody AudioDatum audioDatum){
        audioDatumService.delete(audioDatum);
        return WebResult.okResult();
    }

    @ApiOperation(value = "audioId", notes = "根据音频资源ID 删除资源信息")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @ApiParam(value = "audioId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String audioId){
        audioDatumService.deleteById(String.valueOf(JSONObject.parseObject(audioId).get("AudioId")));
        return WebResult.okResult();
    }

    @PostMapping("/getAudioByAudioId")
    @ApiOperation(value = "audioId", notes = "根据音频资源ID查询音频资源信息")
    public WebResult getAudioByAudioId(@Valid @ApiParam(value = "audioId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String audioId){
        return WebResult.okResult(audioDatumService.getAudioDatumById(String.valueOf(JSONObject.parseObject(audioId).get("AudioId"))));
    }

    @ApiOperation(value = "sortVo", notes = "分页查询音频资源信息")
    @PostMapping("/findAll")
    public WebResult findAll(@Valid @ApiParam(value = "sortVo", name = "分页查询势派资源信息", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(audioDatumService.findAll(sortVo));
    }

    /**
     * 通过音频资源 ID 逻辑删除音频资源信息
     * @param audioId
     * @return
     */
    @ApiOperation(value = "audioId", notes = "删除音频信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    public WebResult deleteIsValidById(@Valid @ApiParam(value = "AudioId", name = "根据资源ID 逻辑删除对应资源信息", required = true) @RequestBody String audioId){
        audioDatumService.deleteIsValidById(audioId);
        return WebResult.okResult();
    }
}
