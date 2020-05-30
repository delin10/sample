package nil.ed.live;

import java.util.ArrayList;
import java.util.List;

public class RoomVo {

    private String roomKey;// 房间key
    private String roomName;// 房间名
    private List<UserVo> userList=new ArrayList<>();// 房间内用户列表

    public List<UserVo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserVo> userList) {
        this.userList = userList;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
