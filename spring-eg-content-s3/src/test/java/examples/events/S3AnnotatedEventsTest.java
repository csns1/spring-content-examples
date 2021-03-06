package examples.events;

import com.github.paulcwarren.ginkgo4j.Ginkgo4jConfiguration;
import com.github.paulcwarren.ginkgo4j.Ginkgo4jSpringRunner;
import examples.s3.S3Config;
import org.junit.runner.RunWith;
import org.springframework.content.s3.config.EnableS3Stores;
import org.springframework.test.context.ContextConfiguration;
import tests.events.AnnotatedEventHandlerConfig;
import tests.events.AnnotatedEventHandlerStoreTests;
import tests.smoke.JpaConfig;

@RunWith(Ginkgo4jSpringRunner.class)
@Ginkgo4jConfiguration(threads=1)
@ContextConfiguration(classes = { JpaConfig.class, S3Config.class, AnnotatedEventHandlerConfig.class })
@EnableS3Stores("examples.stores")
public class S3AnnotatedEventsTest extends AnnotatedEventHandlerStoreTests {
	//
}
