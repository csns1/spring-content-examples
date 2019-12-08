package tests.smoke;

import examples.stores.DocumentStore;
import examples.utils.RandomString;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.content.commons.io.DeletableResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.github.paulcwarren.ginkgo4j.Ginkgo4jDSL.AfterEach;
import static com.github.paulcwarren.ginkgo4j.Ginkgo4jDSL.BeforeEach;
import static com.github.paulcwarren.ginkgo4j.Ginkgo4jDSL.Context;
import static com.github.paulcwarren.ginkgo4j.Ginkgo4jDSL.Describe;
import static com.github.paulcwarren.ginkgo4j.Ginkgo4jDSL.It;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoreTests {

    @Autowired
    private DocumentStore store;

    private Resource r;
    private Exception e;

    {
        Describe("Store", () -> {
            Context("given a new resource", () -> {
                BeforeEach(() -> {
                    r = store.getResource(getId());
                });
                It("should not exist", () -> {
                    assertThat(r.exists(), is(false));
                });
                Context("given content is added to that resource", () -> {
                    BeforeEach(() -> {
                        InputStream is = new ByteArrayInputStream("Hello Spring Content World!".getBytes());
                        OutputStream os = ((WritableResource)r).getOutputStream();
                        IOUtils.copy(is, os);
                        is.close();
                        os.close();
                    });
                    AfterEach(() -> {
                        try {
                            ((DeletableResource) r).delete();
                        } catch (Exception e) {
                            // do nothing
                        }
                    });
                    It("should store that content", () -> {
                        assertThat(r.exists(), is(true));

                        boolean matches = false;
                        InputStream expected = new ByteArrayInputStream("Hello Spring Content World!".getBytes());
                        InputStream actual = null;
                        try {
                            actual = r.getInputStream();
                            matches = IOUtils.contentEquals(expected, actual);
                        } catch (IOException e) {
                        } finally {
                            IOUtils.closeQuietly(expected);
                            IOUtils.closeQuietly(actual);
                        }
                        assertThat(matches, Matchers.is(true));

                    });
                    Context("given that resource is then updated", () -> {
                        BeforeEach(() -> {
                            InputStream is = new ByteArrayInputStream("Hello Updated Spring Content World!".getBytes());
                            OutputStream os = ((WritableResource)r).getOutputStream();
                            IOUtils.copy(is, os);
                            is.close();
                            os.close();
                        });
                        It("should store that updated content", () -> {
                            assertThat(r.exists(), is(true));

                            boolean matches = false;
                            InputStream expected = new ByteArrayInputStream("Hello Updated Spring Content World!".getBytes());
                            InputStream actual = null;
                            try {
                                actual = r.getInputStream();
                                matches = IOUtils.contentEquals(expected, actual);
                            } catch (IOException e) {
                            } finally {
                                IOUtils.closeQuietly(expected);
                                IOUtils.closeQuietly(actual);
                            }
                            assertThat(matches, Matchers.is(true));
                        });
                    });
                    Context("given that resource is then deleted", () -> {
                        BeforeEach(() -> {
                            try {
                                ((DeletableResource) r).delete();
                            } catch (Exception e) {
                                this.e = e;
                            }
                        });
                        It("should not exist", () -> {
                            assertThat(e, is(nullValue()));
                        });
                    });
                });
            });
        });
    }

    protected String getId() {
        RandomString random  = new RandomString(5);
        return "/store-tests/" + random.nextString();
    }
}
