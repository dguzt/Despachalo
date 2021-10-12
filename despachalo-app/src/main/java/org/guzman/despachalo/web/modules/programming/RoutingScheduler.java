package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.programming.application.port.in.GenerateRoutesForDispatchUseCase;
import org.guzman.despachalo.core.programming.application.port.in.GetNextDispatchRoutingGenerationUseCase;
import org.guzman.despachalo.web.config.RoutingVars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class RoutingScheduler {

    private final RoutingVars routingVars;
    private final GetNextDispatchRoutingGenerationUseCase routingGenerationUseCase;
    private final GenerateRoutesForDispatchUseCase routesForDispatchUseCase;
    private final Logger logger = LoggerFactory.getLogger(RoutingScheduler.class);

    @Scheduled(cron = "2 * * * *")
    public void scheduleTaskUsingCronExpression() {

        logger.info("Check for next dispatch routing generation");
        var dispatchOptional = routingGenerationUseCase.execute();
        if (dispatchOptional.isEmpty()) {
            logger.info("SKIPPING. No pending dispatches to generate their routes or already one is executing.");
            return;
        }

        var dispatch =dispatchOptional.get();
        logger.info("PROCESSING. Dispatch routes generating with id: " + dispatch.getId());
        routesForDispatchUseCase.execute(dispatch.getId(), routingVars.getCommonCapacity());
        logger.info("FINISHED. Dispatch routes generated with id: " + dispatch.getId());
    }

}
