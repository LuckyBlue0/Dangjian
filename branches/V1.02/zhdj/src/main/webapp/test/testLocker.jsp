<%@ page import="cn.com.do1.component.distributelock.codecore.Locker" %>
<%@ page import="org.apache.zookeeper.KeeperException" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page import="java.util.logging.Level" %><%
    Logger logger = Logger.getLogger("TEST");
    String connectString = "localhost:3181";
                try {
                    Locker lk = new Locker(connectString, "/locks", 1);
                    Locker lk1 = new Locker(connectString, "/locks1", 1);
                    logger.info(":get locker");
                    boolean result = lk.lock();
                    logger.info(":inside locker" + result);
                    Thread.currentThread().sleep(20 * 1000);
                    //dosomething
                    lk.unlock();
                    logger.info(":out locker" + result);
                    logger.info(":get locker1");
                    boolean result1 = lk1.lock();
                    logger.info(":inside locker1" + result1);
                    Thread.currentThread().sleep(20 * 1000);
                    //dosomething
                    lk1.unlock();
                    logger.info(":out locker1" + result);
                } catch (InterruptedException e) {
                    logger.log(Level.ALL,e.getMessage(),e);
                } catch (KeeperException e) {
                    logger.log(Level.ALL,e.getMessage(), e);
                }
%>