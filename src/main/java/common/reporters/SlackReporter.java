package common.reporters;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import common.ConfigReader;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SlackReporter {
    /**
     * Upload a file to Slack and send it to a specified channel
     * Skip the upload if the SLACK_BOT_TOKEN is not provided,
     * or slack.channel.id key cannot be found in configuration.properties
     * @param message - Additional comment to add to the uploaded test report
     * @param reportPath - path to the file that will be sent
     */
    public static void sendTestReport(String message, String reportPath) {
        var slackApi = Slack.getInstance();
        var authToken = System.getenv("SLACK_BOT_TOKEN");
        if (authToken == null) {
            System.err.println("SLACK_BOT_TOKEN environment variable not provided, skipping report upload to Slack");
            return;
        }
        var channelID = ConfigReader.getProperties().getProperty("slack.channel.id");
        if (channelID == null) {
            System.err.println("slack.channel.id configuration property not provided, skipping report upload to Slack");
            return;
        }
        try {
            var response = slackApi.methods(authToken).filesUploadV2(r -> r
                    .channel(channelID)
                    .initialComment(message)
                    .file(new File(reportPath))
            );
            if (response.isOk()) {
                System.out.printf("Report file %s was successfully sent to Slack", reportPath);
            } else {
                throw new RuntimeException("Error uploading test results to Slack");
            }
        } catch (IOException | SlackApiException exception) {
            throw new RuntimeException(exception);
        }
    }
}
