package com.zy.app.rating.main.plugin;

import com.zy.app.rating.main.plugin.location.LocationPlugin;
import com.zy.app.rating.main.plugin.location.LocationType;
import com.zy.app.rating.main.plugin.location.bestmatch.BestMatch;
import com.zy.app.rating.main.plugin.location.flatrate.FlatRate;
import com.zy.app.rating.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.main.plugin.rating.RatingType;
import com.zy.app.rating.main.plugin.rating.initial.Initial;
import com.zy.app.rating.main.plugin.rating.voice.VoiceStandard;
import com.zy.app.rating.main.plugin.time.TimePlugin;
import com.zy.app.rating.main.plugin.time.TimePluginType;
import com.zy.app.rating.main.plugin.time.advanced.AdvancedTimePlugin;
import com.zy.app.rating.main.plugin.time.flat.FlatTimePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class PluginsConfig {

    @Bean
    public Map<RatingType, RatingPlugin> ratingPlugins() {
        Map<RatingType, RatingPlugin> map = new EnumMap<>(RatingType.class);
        map.put(RatingType.VOICESTANDARD, voiceStandard());
        map.put(RatingType.INITIAL, initial());
        return map;
    }

    @Bean
    public Map<LocationType, LocationPlugin> locationPlugins() {
        Map<LocationType, LocationPlugin> map = new EnumMap<>(LocationType.class);
        map.put(LocationType.BESTMATCH, bestMatch());
        map.put(LocationType.FLAT, flatRate());
        return map;
    }

    @Bean
    public Map<TimePluginType, TimePlugin> timePlugins() {
        Map<TimePluginType, TimePlugin> map = new EnumMap<>(TimePluginType.class);
        map.put(TimePluginType.ADVANCED, advanced());
        map.put(TimePluginType.FLAT, flat());
        return map;
    }

    @Bean public RatingPlugin voiceStandard() {
        return new VoiceStandard();
    }

    @Bean public RatingPlugin initial() {
        return new Initial();
    }

    @Bean public LocationPlugin bestMatch() {
        return new BestMatch();
    }

    @Bean public LocationPlugin flatRate() {
        return new FlatRate();
    }

    @Bean public TimePlugin flat() {
        return new FlatTimePlugin();
    }

    @Bean public TimePlugin advanced() {
        return new AdvancedTimePlugin();
    }
}
