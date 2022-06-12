package pl.mmilewczyk.groupservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mmilewczyk.groupservice.model.dto.GroupRequest;
import pl.mmilewczyk.groupservice.model.dto.GroupResponse;
import pl.mmilewczyk.groupservice.model.dto.GroupResponseLite;
import pl.mmilewczyk.groupservice.service.GroupService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupResponse> createNewGroup(@RequestBody GroupRequest groupRequest) {
        return status(HttpStatus.CREATED).body(groupService.createNewGroup(groupRequest));
    }

    @GetMapping
    public ResponseEntity<Page<GroupResponseLite>> getGroupsByName(@RequestParam("groupName") String groupName) {
        return status(HttpStatus.FOUND).body(groupService.getGroupsByName(groupName));
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable("groupId") Long groupId) {
        return status(HttpStatus.FOUND).body(groupService.getGroupResponseById(groupId));
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupById(@PathVariable("groupId") Long groupId) {
        groupService.deleteGroupById(groupId);
    }

    @PutMapping("/join/{groupId}")
    public ResponseEntity<GroupResponse> joinToGroup(@PathVariable("groupId") Long groupId) {
        return status(HttpStatus.OK).body(groupService.joinToGroup(groupId));
    }

    @PutMapping("/leave/{groupId}")
    public ResponseEntity<GroupResponse> leaveGroup(@PathVariable("groupId") Long groupId) {
        return status(HttpStatus.OK).body(groupService.leaveGroup(groupId));
    }

    @PutMapping("/edit/add/moderator")
    public ResponseEntity<GroupResponse> makeSomeoneAModerator(@RequestParam Long groupId,
                                                               @RequestParam Long userId) {
        return status(HttpStatus.OK).body(groupService.makeSomeoneAModerator(groupId, userId));
    }
}
