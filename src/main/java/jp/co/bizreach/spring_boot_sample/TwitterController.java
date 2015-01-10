package jp.co.bizreach.spring_boot_sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 * @author bizreach.Inc
 */
@Controller
@RequestMapping("")
public class TwitterController {

    @RequestMapping("mypage")
    public String mypage(Model model) throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        ResponseList<Status> timeLineList = twitter.getHomeTimeline();
        User user = twitter.verifyCredentials();
        model.addAttribute("user", user);
        model.addAttribute("timelineList", timeLineList);
        model.addAttribute("text", timeLineList.get(0).getText());
        return "mypage";
    }

    @RequestMapping("doTweet")
    public String doTweet(@RequestParam(value = "tweet", required = true) String name) throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.updateStatus(name);
        return "redirect:/mypage";
    }
}
