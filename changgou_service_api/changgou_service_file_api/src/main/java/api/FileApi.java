package api;

import com.changgou.entity.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FileApi {
    @DeleteMapping("/file/delete")
    public Result deleteFile(@RequestParam("groupName")String groupName, @RequestParam("remoteFileName")String remoteFileName);
}
