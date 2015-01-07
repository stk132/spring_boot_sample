package jp.co.bizreach.spring_boot_sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Controller
@RequestMapping("")
public class SamplerController {
    @RequestMapping("/")
    @ResponseBody
    public String index() throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        // Twitterアカウント名を取得
        // タイムライン
        ResponseList<Status> timeline = twitter.getHomeTimeline();
        List<String> list = new ArrayList<String>();
        for (Status status : timeline) {
            list.add(status.getUser().getName() + status.getText());
        }
        return list.toString();
    }

    @RequestMapping("sample")
    public String sample() {
        return "sample"; // 拡張子必要なし、返したいテンプレートを返す
    }

    @RequestMapping("sample2")
    public String sample2(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "sample2";
    }

    @RequestMapping("subscribe")
    public String subscribe(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "Dummy") String name) {
        model.addAttribute("name", name);
        return "/subscribe";
    }
}
