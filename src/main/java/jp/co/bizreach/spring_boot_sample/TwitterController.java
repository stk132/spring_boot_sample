package jp.co.bizreach.spring_boot_sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Controller
@RequestMapping("")
public class TwitterController {
    @RequestMapping("twitter1")
    public String twitter1(Model model) throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        ResponseList<Status> timeline = twitter.getHomeTimeline();
        //twitter.updateStatus("tabeさん");
        model.addAttribute("text", timeline.get(0).getText());
        return "twitter1";
    }

    @RequestMapping("twitter2")
    public String twitter2(@RequestParam(value = "tweet", required = true) String name) throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.updateStatus(name);
        return "redirect:/twitter1";
    }

    @RequestMapping("twitter3")
    public String twitter3(Model model) throws TwitterException {
        List<MockStatus> list = new ArrayList<MockStatus>();
        list.add(new MockStatus("aaaa"));
        list.add(new MockStatus("bbbb"));
        list.add(new MockStatus("cccc"));
        list.add(new MockStatus("ddddd"));
        list.add(new MockStatus("eeeee"));
        model.addAttribute("list", list);
        return "twitter3";
    }

    public class MockStatus {
        public String tweet;

        public MockStatus(String tweet) {
            this.tweet = tweet;
        }
    }
}
