package uz.futbolteam.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.futbolteam.domain.Message;
import uz.futbolteam.domain.User;
import uz.futbolteam.repo.MessageRepo;
import uz.futbolteam.repo.PlayerRepo;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private PlayerRepo playerRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Model model,@AuthenticationPrincipal User user){
        model.addAttribute("user",user);
        return "greeting";

    }
    @GetMapping("/main")
    public String Main(Model model,@AuthenticationPrincipal User user){
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);
        model.addAttribute("user",user);
        return "main";

    }
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
            message.setOwner(user);
            if (bindingResult.hasErrors()){
                Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
                model.mergeAttributes(errorsMap);
                model.addAttribute("message",message);
            }
            else {
                saveFile(message, file);
                model.addAttribute("message",null);
                messageRepo.save(message);
            }
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute( "messages",messages);

        return "main";
    }

    private void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultfilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultfilename));
            message.setFilename(resultfilename);
        }
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String,Object> model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        } else
        {
            messages = messageRepo.findAll();
        }

        model.put("messages",messages);
        return "main";
    }

    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message

    ){
        Set<Message> messages = user.getMessages();

        model.addAttribute("userChannel",user);
        model.addAttribute("subscriptionsCount",user.getSubscriptions().size());
        model.addAttribute("subscribersCount",user.getSubscribers().size());
        model.addAttribute("isSubscriber",user.getSubscribers().contains(currentUser));

        model.addAttribute("messages",messages);
        model.addAttribute("message",message);
        model.addAttribute("isCurrentUser",currentUser.equals(user));
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
            ) throws IOException {
               if (message.getOwner().equals(currentUser)){
                   if (!StringUtils.isEmpty(text)){
                       message.setText(text);
                   }
                   if (!StringUtils.isEmpty(tag)){
                       message.setTag(tag);
                   }
                   saveFile(message, file);
                messageRepo.save(message);
               }
            return "redirect:/user-messages/" + user.getId();
    }
}
