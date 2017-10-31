package ru.colibri.ui.settings.server;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AppiumServerTest {

    @InjectMocks
    AppiumServer server;

    @Mock
    IServerConfig config;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void startAndStopServer() throws Exception {
        when(config.getHost()).thenReturn("0.0.0.0");
        when(config.getPort()).thenReturn("4723");
        server.startServer();
        server.stopServer();
    }

    @Test(expected = IllegalArgumentException.class)
    public void startServerWithWrongHost() throws Exception {
        when(config.getHost()).thenReturn("1.2.3.4.5.6.7");
        when(config.getPort()).thenReturn("4723");
        server.startServer();

    }

    @Test(expected = IllegalArgumentException.class)
    public void startServerWithWrongPort() throws Exception {
        when(config.getHost()).thenReturn("0.0.0.0");
        when(config.getPort()).thenReturn("-4723");
        server.startServer();

    }

}